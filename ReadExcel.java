package vikrant;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;



public class ReadExcel {


	XSSFWorkbook wb;
	XSSFSheet s;

	public ReadExcel(String inputFile) 
	{
		try 
		{
			File file = new File(inputFile);
			FileInputStream inputWorkbook = new FileInputStream(file);
			
			wb =new XSSFWorkbook(inputWorkbook);
		}
		catch (Exception e) 
		{
			System.out.println("error");
		}
	}
	
	public void ShowExcel(int sheetnum ) 
	{
		s = wb.getSheetAt(sheetnum);
		
		/*System.out.println(s.getLastRowNum());
		System.out.println(s.getRow(0).getLastCellNum());
		System.out.print(",");*/
		
		for(int j= 0;j<s.getLastRowNum()+1;j++){
			 XSSFRow row = s.getRow(j);
			 //System.out.println(s.getRow(j).getLastCellNum());
			 System.out.println( );
			 for (int i=0;i<s.getRow(j).getLastCellNum(); i++) {

			   //XSSFCell cell = row.getCell(i, XSSFRow.CREATE_NULL_AS_BLANK);
			   XSSFCell cell = row.getCell(i);
			   
			   //System.out.print( cell.getCellStyle());
			   if (cell == null) {
				   send_aurdino<String> gt = new send_aurdino<String>("\t");
				   gt.send();
			   }
			   else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
			   {
				   send_aurdino<String> gt = new send_aurdino<String>(cell.getStringCellValue());
				   gt.send();
			   }
			   else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
			   {
				   send_aurdino<Double> gt = new send_aurdino<Double>(cell.getNumericCellValue());
				   gt.send();
			   }
			   else if(cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA)
			   {
				   send_aurdino<String> gt = new send_aurdino<String>(cell.getCellFormula());
				   gt.send();
			   }
			   else if(cell.getCellType() == XSSFCell.CELL_TYPE_BLANK)
			   {
				   send_aurdino<String> gt = new send_aurdino<String>(" ");
				   gt.send();
			   }
			   else if(cell.getCellType() == XSSFCell.CELL_TYPE_ERROR)
			   {
			   	System.out.print(" ");
			   }
			   
			  }
			 }
		
	}
	
}
