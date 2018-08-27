import java.sql.*;

public class Uov_Primary_1 {

    public static void main(String [] args) throws Exception{

        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;
        String inputTable = "iptable_";
        String outputTable = "optable_";
        String selectSql = null;
        String truncateSql = null;

        String runId = args[0];
        String uovId = "1";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mdt_poc","root","zim");
            stmt = con.createStatement();

            inputTable = inputTable + runId + "_" + uovId;

            System.out.println(inputTable);
            outputTable = outputTable + "primary_" + uovId ;

            selectSql = String.format("select city, count(*) as count from %s group by city", inputTable);
            rs = stmt.executeQuery(selectSql);

            while(rs.next()) {
                  String city= rs.getString("city");
                  String count = rs.getString("count");
                  String insertSql = "insert into " + outputTable + " (city, count, runid) values (" + "\"" + city + "\"" + " ,"
                            + count + "," + "\"" + runId + "\"" + ")";
                  System.out.println("Executing statement : " + insertSql);
                  ps = con.prepareStatement(insertSql);
                  ps.executeUpdate();
            }

           con.close();

        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("**************************************");
        }
    }
}
