package cn.wolfcode.crm2.web.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.wolfcode.crm2.domain.Employee;
import cn.wolfcode.crm2.domain.Pay;
import cn.wolfcode.crm2.page.PageResult;
import cn.wolfcode.crm2.query.PayQueryObject;
import cn.wolfcode.crm2.service.IPayService;
import cn.wolfcode.crm2.util.AjaxResult;
import cn.wolfcode.crm2.util.ExcelUtil;
import jxl.Sheet;
import jxl.Workbook;

@Controller
public class PayController {
	@Autowired
	private IPayService payService;
	
	@RequestMapping("/pay")
	public String index(){
		return "pay";
	}
	
	@RequestMapping("/pay_list")
	@ResponseBody
	public PageResult list(PayQueryObject qo){
		return payService.queryForPage(qo);
	}
	
	@RequestMapping("/pay_save")
	@ResponseBody
	public AjaxResult save(MultipartFile file){
		AjaxResult result = null;
		try {
			//找到需要读取的文件
			Workbook workbook = Workbook.getWorkbook(file.getInputStream());
			//拿到工作本
			Sheet sheet = workbook.getSheet(0);
			Pay pay;
			Employee emp;
			List<Pay> list = new ArrayList<>();
			for (int i = 1; i < sheet.getRows(); i++) {
				pay = new Pay();
				pay.setSn(Long.valueOf(sheet.getCell(0, i).getContents()));
				pay.setYear(Long.valueOf(sheet.getCell(1, i).getContents()));
				pay.setMonth(Long.valueOf(sheet.getCell(2, i).getContents()));
				pay.setPay(new BigDecimal(sheet.getCell(3, i).getContents()));
				emp = new Employee();
				emp.setUsername(sheet.getCell(4, i).getContents());
				pay.setEmp(emp);
				System.out.println(pay.toString());
				list.add(pay);
			}
			payService.insertAll(list);
			result = new AjaxResult(true, "上传成功");
		} catch (Exception e) {
			result = new AjaxResult("上传失败,请联系管理员!");
			System.out.println(result.getSuccess());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/pay_download")
	public ResponseEntity<byte[]> download(@RequestHeader("User-Agent")String userAgent, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Pay> pays = payService.queryForList();
		String dir = request.getServletContext().getRealPath("/WEB-INF/down");
		File file = new File(dir,fileName);
		ExcelUtil.getExcel(file, pays);
		HttpHeaders headers = new HttpHeaders();
		if(userAgent.contains("IE")) {
			//如果是IE
			headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "utf-8"));
		}else {
			headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
		}
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<> (FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
}
