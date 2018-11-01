import java.io.IOException;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TriggerUovComparator {


    public static void main(String[] args) throws Exception {
        System.out.println("**************************************");

        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mdt_poc", "root", "zim");
            //ps = con.prepareStatement("insert into parameters values(?,?)");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from tr_details");


            while (rs.next()) {
                String[] uovList = rs.getString("uovlist").split(",");
                String runid = rs.getString("runid");


                for (String uov : uovList) {
                    ps = con.prepareStatement("insert into parameters values(?, ?)");
                    ps.setString(1, runid);
                    ps.setString(2, uov);
                    ps.executeUpdate();

                    //int returnCode = ps.executeUpdate("truncate table parameters");
                    //if (returnCode == 0) {
                    String compScript = "/home/mdt-worker/poc/work/Uov_Comparator_";
                    //ps.setInt(1,runid);
                    //ps.setInt(2,Integer.parseInt(uov));
                    //ps.executeUpdate();
                    compScript = compScript + uov + ".sh";
                    triggerScript(compScript, runid);
                    System.out.println(compScript+"\n");
                   // ps = con.prepareStatement("truncate table parameters");
                    //ps.executeUpdate();

                    //}
                }

            }
//            int returnCode = ps.executeUpdate("truncate table parameters");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
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
