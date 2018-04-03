package excel2XMl;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class Exml {
    public static void main(String[] args) throws Exception {
        String encoding = "UTF8";

        File fi = new File("C:" + File.separator + "Users" + File.separator + "dk" + File.separator + "Desktop" + File.separator + "tc1.xls");
        FileInputStream fis = new FileInputStream(fi);
        Workbook wb = Workbook.getWorkbook(fi);
        for (int k = 0; k < wb.getNumberOfSheets(); k++) {
            Sheet st = wb.getSheet(k);
            String fileName = st.getName() + ".xml";
            File fo = new File("C:" + File.separator + "Users" + File.separator + "dk" + File.separator + "Desktop" + File.separator + "Testcase" + File.separator + fileName);
            FileOutputStream out = new FileOutputStream(fo);
            int rowNum = st.getRows();
            int colNum = st.getColumns();
            ArrayList tcList = new ArrayList();
            String detail = "22";
            if (st.getRow(0).length!=1){
                detail = st.getRow(0)[1].getContents();
            }

            Cell[] title = null;
            for (int i = 1; i < rowNum; i++) {
                if (i == 1) {
                    title = st.getRow(i);
                } else {
                    Cell[] rowContent = st.getRow(i);
                    Txml txm = paserUnit(title, rowContent);
                    tcList.add(txm);

                }

            }
            System.out.println(rowNum);
            CreateXml cx = new CreateXml("utf-8", out);
            cx.generateXML(tcList,st.getName(),k+1,detail);

        }

    }


    public static Txml paserUnit(Cell[] title, Cell[] rowContent) {
        ArrayList titlelist = new ArrayList();
        Txml txm = new Txml();
        for (int i = 0; i < rowContent.length; i++) {
            Cell txmCell = title[i];
            String txmc = txmCell.getContents();
            Cell cellContent = rowContent[i];
            String cellCon = cellContent.getContents();
            if (Texcel.CASENAME_VALUE.equals(txmc)) {
                txm.setCaseName((cellCon));
            }
            if (Texcel.SUMMARY_VALUE.equals(txmc)) {
                txm.setSummary(cellCon);
            }
            if (Texcel.PRECONDITIONS_VALUE.equals(txmc)) {
                txm.setPreconditions(cellCon);
            }
            if (Texcel.IMPORTANCE_VALUE.equals(txmc)) {
                txm.setImportance(validateInteger(cellCon));
            }
            if (Texcel.STEPS_VALUE.equals(txmc)) {
                txm.setSteps(cellCon);
            }

        }
        return txm;
    }

    public static Integer validateInteger(String str) {
        Integer result = null;
        if (str == null || str.equals("")) {
            result = new Integer("0");
            return result;
        } else {
            return new Integer(str);
        }

    }

    public void genratexml(String filepath, String strorepath) throws Exception {
        String encoding = "UTF8";
        System.out.println(filepath);
        System.out.println(strorepath);
        File fi = new File(filepath);
        FileInputStream fis = new FileInputStream(fi);
        Workbook wb = Workbook.getWorkbook(fi);
        for (int k = 0; k < wb.getNumberOfSheets(); k++) {
            Sheet st = wb.getSheet(k);
            String fileName = st.getName() + ".xml";
            File fo = new File(strorepath + File.separator + fileName);
            FileOutputStream out = new FileOutputStream(fo);
            int rowNum = st.getRows();
            int colNum = st.getColumns();
            ArrayList tcList = new ArrayList();
            String detail = null;
            if (st.getRow(0).length == 1) {
                detail = "";
            } else {
                detail = st.getRow(0)[1].getContents();
            }
            Cell[] title = null;
            System.out.println(rowNum);
            for (int i = 1; i < rowNum; i++) {
                if (i == 1) {
                    title = st.getRow(i);
                } else {
                    Cell[] rowContent = st.getRow(i);
                    Txml txm = paserUnit(title, rowContent);
                    tcList.add(txm);

                }

            }
            CreateXml cx = new CreateXml("utf-8", out);
            cx.generateXML(tcList, st.getName(), k + 1, detail);

        }
    }
}
