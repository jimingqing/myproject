package com.yrtech.wx.capp.portal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**  
 *   
 * @author (版权归原作者) 用于读取excel  
 */    
    
public class ExcelReader {    
    
    private HSSFWorkbook wb = null;// book [includes sheet]    
    private HSSFSheet sheet = null;    
    private HSSFRow row = null;    
    private int sheetNum = 0; // 第sheetnum个工作表    
    private int rowNum = 0;    
    
    private FileInputStream fis = null;    
    private File file = null;    
    
    public ExcelReader() {    
    
    }    
    
    public ExcelReader(File file) {    
        this.file = file;    
    }    
    
    public void setRowNum(int rowNum) {    
        this.rowNum = rowNum;    
    }    
    
    public void setSheetNum(int sheetNum) {    
        this.sheetNum = sheetNum;    
    }    
    
    public void setFile(File file) {    
        this.file = file;    
    }    
    
    // 读取excel文件获得HSSFWorkbook对象    
    public void open() throws IOException {    
        fis = new FileInputStream(file);    
        wb = new HSSFWorkbook(new POIFSFileSystem(fis));    
        fis.close();    
    }    
    
    /**  
     * 返回sheet表数目  
     *   
     * @return int  
     */    
    public int getSheetCount() {    
        int sheetCount = -1;    
        sheetCount = wb.getNumberOfSheets();    
        return sheetCount;    
    }    
    
    /**  
     * sheetNum下的记录行数  
     *   
     * @return int  
     */    
    public int getRowCount() {    
        if (wb == null)    
            System.out.println("=============>WorkBook为空");    
        HSSFSheet sheet = wb.getSheetAt(this.sheetNum);    
        int rowCount = -1;    
        rowCount = sheet.getLastRowNum();    
        return rowCount;    
    }    
    
    /**  
     * 读取指定sheetNum的rowCount  
     *   
     * @param sheetNum  
     * @return int  
     */    
    public int getRowCount(int sheetNum) {    
        HSSFSheet sheet = wb.getSheetAt(sheetNum);    
        int rowCount = -1;    
        rowCount = sheet.getLastRowNum();    
        return rowCount;    
    }    
    
    /**  
     * 得到指定行的内容  
     *   
     * @param lineNum  
     * @return String[]  
     */    
    public String[] readExcelLine(int lineNum, String type) {    
        return readExcelLine(this.sheetNum, lineNum, type);    
    }    
    
    // 指定工作表和行数的内容    
    public String[] readExcelLine(int sheetNum, int lineNum, String type) {    
        if (sheetNum < 0 || lineNum < 0)    
            return null;    
        String[] strExcelLine = null;    
        try {    
            sheet = wb.getSheetAt(sheetNum);    
            row = sheet.getRow(lineNum);    
            int cellCount = row.getLastCellNum();    
            strExcelLine = new String[cellCount + 1];    
            for (int i = 0; i <= cellCount; i++) {    
    
                strExcelLine[i] = readStringExcelCell(lineNum, i, type);    
            }    
        } catch (Exception e) {    
    
            e.printStackTrace();    
        }    
        return strExcelLine;    
    }    
    
    // 读取指定列的内容    
    public String readStringExcelCell(int cellNum) {    
        return readStringExcelCell(this.rowNum, cellNum, "string");    
    }    
    
    // 指定行和列编号的内容    
    public String readStringExcelCell(int rowNum, int cellNum, String type) {    
        return readStringExcelCell(this.sheetNum, rowNum, cellNum, type);    
    }    
    
    // 指定工作表、行、列下的内容    
    public String readStringExcelCell(int sheetNum, int rowNum, int cellNum, String type) {    
        if (sheetNum < 0 || rowNum < 0)    
            return "";    
        String strExcelCell = "";    
        try {    
            sheet = wb.getSheetAt(sheetNum);    
            row = sheet.getRow(rowNum);   
            long dd ;
            if (row.getCell(cellNum) != null) { // add this condition    
                switch (row.getCell(cellNum).getCellType()) {    
                case HSSFCell.CELL_TYPE_FORMULA:    
                    strExcelCell = "FORMULA ";    
                    break;    
                case HSSFCell.CELL_TYPE_NUMERIC: {  
                	if(type.equals("date")){
                		strExcelCell = getDateCellValue(row.getCell(cellNum));
                	}else{
                		dd = (long)(row.getCell(cellNum)    
	                            .getNumericCellValue());  
	                    strExcelCell = dd+"";
                	}
                }    
                    break;    
                case HSSFCell.CELL_TYPE_STRING:    
                    strExcelCell = row.getCell(cellNum)    
                            .getStringCellValue();    
                    break;    
                case HSSFCell.CELL_TYPE_BLANK:    
                    strExcelCell = "";    
                    break;    
                default:    
                    strExcelCell = "";    
                    break;    
                }    
            }    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        return strExcelCell;    
    }    
    
    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }
    
    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }
    
    /**  
     * Fuction 根据文件的路径 解析excel文件   
     *   
     */    
    /*public List<Student> getStuExcle(String filePath) {    
        System.out.println("excel reader:"+filePath);    
        File file = new File(filePath);    
        ExcelReader readExcel = new ExcelReader(file);    
        // 打开文件    
        try {    
            readExcel.open();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
        readExcel.setSheetNum(0); // 设置读取索引为0的工作表    
        // 总行数    
        int count = readExcel.getRowCount();    
        List<Student> ls = new ArrayList<Student>();    
        // 循环读取Excel文件中的内容    
        for (int i = 1; i <= count; i++) {    
            String[] rows = readExcel.readExcelLine(i);    
            Student student = new Student();    
            if (rows.length >= 23) {    
                System.out.println("row"+rows[0]);    
                student.setStuId(Integer.valueOf(rows[0]));    
                student.setStuNo(rows[1]);//学号    
                student.setStuName(rows[2]);//姓名    
                student.setStuBeforeName(rows[3]);//曾用名    
                student.setStuSex(rows[4]); //性别    
                student.setStuBirth(rows[5]);//出生日期    
                student.setStuMinZu(rows[6]);//名族    
                student.setStuOrigin(rows[7]);//籍贯    
                student.setStuPolity(rows[8]);//政治面貌    
                student.setStuCollege(rows[9]);//学院    
                student.setStuProName(rows[10]);//专业名称    
                student.setStuClass(rows[11]); //行政班级    
                student.setStuInDate(rows[12]);//入学日期    
                student.setStuGraduateSchool(rows[13]);//毕业中学    
                student.setStuDorm(rows[14]);//宿舍号    
                student.setStuPhone(rows[15]);//联系电话    
                student.setStuPostalCode(rows[16]);//邮政编码    
                student.setStuIdCard(rows[17]);//身份证号    
                student.setStuFatherName(rows[18]); //父亲姓名    
                student.setStuMotherName(rows[19]);//母亲姓名    
                student.setStuAddress(rows[20]);//家庭住址    
                student.setStuParentPhone(rows[21]);//家长联系方式    
                student.setStuPicture(rows[22]);//头像    
                System.out.println("uuuuu"+student.getStuId());    
                ls.add(student);    
            }    
        }    
        return ls;    
    }    */
    
    public static void main(String[] args){
    	File file = new File("f:\\user.xls");    
        ExcelReader readExcel = new ExcelReader(file);  
     // 打开文件    
        try {    
            readExcel.open();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
        readExcel.setSheetNum(0); // 设置读取索引为0的工作表    
        // 总行数    
        int count = readExcel.getRowCount();   
        for(int i =0;i<count;i++){
        	System.out.println(readExcel.readExcelLine(i, "string")[0]);
        }
    }
        
}    
