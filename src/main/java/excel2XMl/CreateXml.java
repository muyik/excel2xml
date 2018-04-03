package excel2XMl;


import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateXml {
    String encoding;
    OutputStream out;

    CreateXml(String enc, OutputStream out) {
        encoding = enc;
        this.out = out;
        if (encoding == null || !encoding.equals("UnicodeBig")) {
            this.encoding = "UTF8";
        }
    }

    public void generateXML(ArrayList tcList, String str, int i, String detail) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(out, encoding);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
        bw.newLine();
        bw.write("<testsuite   name=\"" + str + "\" >");
        bw.newLine();
        bw.write("<node_order><![CDATA[" + i + "]]></node_order>");
        bw.newLine();
        bw.write("<details><![CDATA[<P>" + detail + "</p>]]></details>");
        bw.newLine();
        Iterator it = tcList.iterator();
        int j = 1;
        while (it.hasNext()) {
            Txml txm = (Txml) it.next();
            bw.write("<testcase  name=\"" + txm.getCaseName() + "\">");
            bw.newLine();
            bw.write("<node_order><![CDATA[" + j + "]]></node_order>");
            bw.newLine();
            bw.write("<version><![CDATA[1]]></version>");
            bw.newLine();
            bw.write("<summary><![CDATA[<p>" + txm.getSummary() + "</p>]]></summary>");
            bw.newLine();
            bw.write("<preconditions><![CDATA[<p>" + txm.getPreconditions() + "</p>]]></preconditions>");
            bw.newLine();
            bw.write("<execution_type><![CDATA[1]]></execution_type>");
            bw.newLine();
            bw.write("<importance><![CDATA[" + txm.getImportance() + "]]></importance>");
            bw.newLine();
            bw.write("<estimated_exec_duration></estimated_exec_duration>");
            bw.newLine();
            bw.write("<status>1</status>");
            bw.newLine();
            bw.write("<steps>");
            bw.newLine();
            String[] s = txm.getSteps().split("\n");
            if (s.length > 0) {
                for (int n = 0; n < s.length; n++) {
                    String[] step = s[n].split(":");
                    if (step.length>1) {
                        bw.write("<step>");
                        bw.newLine();
                        bw.write("<step_number><![CDATA[" + (n + 1) + "]]></step_number>");
                        bw.newLine();
                        bw.write("<actions><![CDATA[<p>" + step[0] + "</p>]]></actions>");
                        bw.newLine();
                        bw.write("<expectedresults><![CDATA[<p>" + step[1] + "</p>]]></expectedresults>");
                        bw.newLine();
                        bw.write("<execution_type><![CDATA[1]]></execution_type>");
                        bw.newLine();
                        bw.write("</step>");
                        bw.newLine();
                    }
                }
            }
            bw.write("</steps>");
            bw.newLine();
            bw.write("</testcase>");
            bw.newLine();
            j = j + 1;
        }
        bw.write("</testsuite>");
        bw.newLine();

        bw.flush();
        bw.close();
    }

}
