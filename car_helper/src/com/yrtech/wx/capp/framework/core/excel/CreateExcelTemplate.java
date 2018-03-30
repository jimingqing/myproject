package com.yrtech.wx.capp.framework.core.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddressList;

public class CreateExcelTemplate {
	
	private static Logger logger = Logger.getLogger(CreateExcelTemplate.class);
	
	private HSSFWorkbook wb ;
	private HSSFSheet sheet ;
	private File file;
	
	public void createExcelFile(String fileName){
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();
			logger.info("生成文件成功！");
			
			file = new File(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("生成文件失败！");
		}
	}
	
	public void createTitle(List<String> titleList){
		if(titleList == null || titleList.size()==0){
			logger.error("标题数据错误，请设置标题数据");
			return;
		}
		
		if(sheet == null){
			logger.error("sheet不能为空，请设置sheet");
			return;
		}
		
		int rownum = 0; //标题行
		HSSFRow row = sheet.createRow(rownum);
		HSSFCell cell;
		for(int i=0; i<titleList.size(); i++){
			cell = row.createCell(i);
			cell.setCellValue(titleList.get(i));//
			cell.setCellStyle(getTitleStyle()); 
			sheet.setColumnWidth(i, 3000);
		}
		row.setHeight(Short.parseShort("500"));
		
		logger.info("创建标题内容成功！");
	}
	
	public void setDataValidation(String[] array, int row, int col){
		// 设置下拉列表的内容
		if(array.length == 0){
			logger.error("下拉列表数据错误！");
			return ;
		}

		// 加载下拉列表内容
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(array);
		// 设置数据有效性加载在哪个单元格上。

		// 四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(1, row, col, col);
		// 数据有效性对象
		HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);

		this.sheet.addValidationData(data_validation_list);
	}
	
	/**    
     * 设置模板文件的横向表头单元格的样式    
     * @param wb    
     * @return    
     */     
    private CellStyle getTitleStyle(){      
        CellStyle style = wb.createCellStyle();      
        //对齐方式设置      
        style.setAlignment(CellStyle.ALIGN_CENTER);  
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //边框颜色和宽度设置      
        style.setBorderBottom(CellStyle.BORDER_THIN);      
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());      
        style.setBorderLeft(CellStyle.BORDER_THIN);      
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());      
        style.setBorderRight(CellStyle.BORDER_THIN);      
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());      
        style.setBorderTop(CellStyle.BORDER_THIN);      
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());      
        style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());      
        //设置背景颜色      
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());      
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);      
        //粗体字设置      
        Font font = wb.createFont();      
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);      
        style.setFont(font);      
        return style;      
    }  
    
    
     /**
      * 三级联动数据有效性设置，创建隐藏sheet，保存三级联动数据
      * @param sheetName
      * @param map1 ： 一级与二级数据映射
      * @param map2 ： 二级与三级数据映射
      */
    public void createDateValidationSheetTwo(String sheetName, Map<String,List<String>> map1, Map<String,List<String>> map2){
    	if(sheetName == null){
    		sheetName = "hidden";
    	}
    	HSSFSheet hidden = wb.createSheet(sheetName);
    	HSSFRow row ;
		HSSFCell cell ;
		int i = 0;
		int j = 0;
    	for(String tmp:map1.keySet()){
    		j=0;
    		row = hidden.createRow(i);
    		cell = row.createCell(j);
    		cell.setCellValue(tmp);
    		for(String tmp1 : map1.get(tmp)){
    			cell = row.createCell(j+1);
        		cell.setCellValue(tmp1);
        		j++;
    		}
    		String expression = sheetName+"!$"+int2Column(1)+"$"+(i+1)+":$"+int2Column(j)+"$"+(i+1);
    		createName(tmp, expression);
			i++;
    	}
    	for(String tmp:map2.keySet()){
    		j=0;
    		row = hidden.createRow(i);
    		cell = row.createCell(j);
    		cell.setCellValue(tmp);
    		for(String tmp1 : map2.get(tmp)){
    			cell = row.createCell(j+1);
        		cell.setCellValue(tmp1);
        		j++;
    		}
    		if(j==0) j++;
    		String expression = sheetName+"!$"+int2Column(1)+"$"+(i+1)+":$"+int2Column(j)+"$"+(i+1);
    		createName(tmp, expression);
			i++;
    	}
    	createName(sheetName+"_key_name", sheetName+"!$A$1:$A$"+map1.size());
    	
    	wb.setSheetHidden(wb.getSheetIndex(sheetName), true);
    }
    
    /**
     * 二级联动数据有效性设置，创建隐藏sheet，保存二级联动数据
     * @param sheetName
     * @param map ： 一级与二级数据映射
     */
    public void createDataValidationSheet(String sheetName, Map<String,List<String>> map){
    	if(sheetName == null){
    		sheetName = "hidden";
    	}
    	HSSFSheet hidden = wb.createSheet(sheetName);
    	HSSFRow row ;
		HSSFCell cell ;
		int i = 0;
		int j = 0;
    	for(String tmp:map.keySet()){
    		j=0;
    		row = hidden.createRow(i);
    		cell = row.createCell(j);
    		cell.setCellValue(tmp);
    		for(String tmp1 : map.get(tmp)){
    			cell = row.createCell(j+1);
        		cell.setCellValue(tmp1);
        		j++;
    		}
    		String expression = sheetName+"!$"+int2Column(1)+"$"+(i+1)+":$"+int2Column(j)+"$"+(i+1);
    		createName(tmp, expression);
			i++;
    	}
    	createName(sheetName+"_key_name", sheetName+"!$A$1:$A$"+map.size());
    	
    	wb.setSheetHidden(wb.getSheetIndex(sheetName), true);
    }
    
    /** 
     * 创建名称 
     * @param wb 
     * @param name 
     * @param expression 
     * @return 
     */  
    public HSSFName createName(String name, String expression){  
        HSSFName refer = wb.createName();  
        refer.setRefersToFormula(expression);  
        refer.setNameName(name);  
        return refer;  
    }  

    /** 
     * 设置数据有效性（通过名称管理器级联相关） 
     * @param name 
     * @param firstRow 
     * @param endRow 
     * @param firstCol 
     * @param endCol 
     * @return 
     */  
    public static HSSFDataValidation setDataValidation(String name, int firstRow, int endRow, int firstCol, int endCol){  
        //设置下拉列表的内容   
        logger.info("起始行:" + firstRow + "___起始列:" + firstCol + "___终止行:" + endRow + "___终止列:" + endCol);  
        //加载下拉列表内容   
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(name);  
        // 设置数据有效性加载在哪个单元格上。   
        // 四个参数分别是：起始行、终止行、起始列、终止列   
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);  
        // 数据有效性对象   
        HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);  
        return data_validation;  
    }
    
     /**
      * 数字到列号的转换
      * @param column
      * @return 
      */
    public static String int2Column(final int column){   
        int col = column + 1;   
        int system = 26;   
        char[] digArr = new char[100];   
        int ind = 0;   
        while (col > 0)    
        {   
            int mod = col % system;   
            if (mod == 0) mod = system;   
            digArr[ind++] = dig2Char(mod);   
            col = (col - 1) / 26;   
        }   
        StringBuffer bf = new StringBuffer(ind);   
        for (int i = ind - 1; i >= 0; i--)    
        {   
            bf.append(digArr[i]);   
        }   
        return bf.toString();        
    }   
       
       
    private static char dig2Char(final int dig){   
        int acs = dig - 1 + 'A';   
        return (char)acs;        
    }
    
	public HSSFWorkbook getWb() {
		return wb;
	}
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}
	public HSSFSheet getSheet() {
		return sheet;
	}
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
