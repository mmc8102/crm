package cn.wolfcode.crm2.util;

import java.io.File;
import java.util.List;

import cn.wolfcode.crm2.domain.Pay;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	public static void getExcel(File file, List<Pay> pays) {
		try {
			//创建Excel对象
			WritableWorkbook wb = Workbook.createWorkbook(file);
			//创建工作本
			WritableSheet sheet = wb.createSheet("工资表", 0);
			Pay pay;
			Label cell;
			cell = new Label(0, 0, "编号");
			sheet.addCell(cell);
			cell = new Label(1, 0, "年份");
			sheet.addCell(cell);
			cell = new Label(2, 0, "月份");
			sheet.addCell(cell);
			cell = new Label(3, 0, "工资");
			sheet.addCell(cell);
			cell = new Label(4, 0, "员工");
			sheet.addCell(cell);
			for (int i = 0; i < pays.size(); i++) {
				pay = pays.get(i);
				cell = new Label(0, i+1, pay.getSn().toString());
				sheet.addCell(cell);
				cell = new Label(1, i+1, pay.getYear().toString());
				sheet.addCell(cell);
				cell = new Label(2, i+1, pay.getMonth().toString());
				sheet.addCell(cell);
				cell = new Label(3, i+1, pay.getPay().toString());
				sheet.addCell(cell);
				cell = new Label(4, i+1, pay.getEmp().getUsername());
				sheet.addCell(cell);
			}
			wb.write();
			wb.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
