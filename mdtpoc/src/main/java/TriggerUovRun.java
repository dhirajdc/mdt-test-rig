import java.io.IOException;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TriggerUovRun {

    public static void main(String [] args) throws Exception{
        System.out.println("**************************************");

        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mdt_poc","root","zim");
            //ps = con.prepareStatement("insert into parameters values(?,?)");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from tr_details");



            while(rs.next()){
                String[] runids = rs.getString("runid").split(",");
                String[] uovList = rs.getString("uovlist").split(",");
                for (String runid:runids) {
                    for (String uov : uovList) {
                        String primScript = "/home/mdt-worker/poc/work/Uov_Primary_";
                        String testScript = "/home/mdt-worker/poc/work/Uov_Test_";

                        primScript = primScript + uov + ".sh";
                        System.out.println(primScript + "\n");
                        triggerScript(primScript, runid);

                        testScript = testScript + uov + ".sh" ;
                        System.out.println(testScript + "\n");
                        triggerScript(testScript, runid);
                    }
                }

            }
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        finally {
            System.out.println("**************************************");
        }
    }

    public static void triggerScript(String scriptName, String parameter){
        //String[] cmdScript = new String[]{"/bin/bash", scriptName};
        String[] cmdScript = {scriptName, parameter};


        try {
            System.out.println("Executing the script " + cmdScript[0] + " " + cmdScript[1]);
            Process proc = Runtime.getRuntime().exec(cmdScript);
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


}
