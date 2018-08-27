import java.io.IOException;
import java.sql.*;

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
                String[] uovList = rs.getString(3).split(",");
                String runid = rs.getString("runid");

                ps = con.prepareStatement("insert into parameters values(?)");
                ps.setString(1, runid);
                ps.executeUpdate();

                for (String uov : uovList) {
                    //int returnCode = ps.executeUpdate("truncate table parameters");
                    //if (returnCode == 0) {
                    String compScript = "./Uov_Comparator_";
                    //ps.setInt(1,runid);
                    //ps.setInt(2,Integer.parseInt(uov));
                    //ps.executeUpdate();
                    compScript = compScript + uov + ".sh";
                    triggerScript(compScript);
                    System.out.println(compScript+"\n");
                    Thread.sleep(30000);

                    //}
                }

                ps = con.prepareStatement("truncate table parameters");
                ps.executeUpdate();
            }
//            int returnCode = ps.executeUpdate("truncate table parameters");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("**************************************");
        }
    }

    public static void triggerScript(String scriptName){
        String[] cmdScript = new String[]{"/bin/bash", scriptName};
        try {
            Runtime.getRuntime().exec(cmdScript);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
