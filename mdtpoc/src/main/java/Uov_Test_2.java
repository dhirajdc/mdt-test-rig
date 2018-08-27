import java.sql.*;

public class Uov_Test_2 {

    public static void main(String [] args) throws Exception{

        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;
        String inputTable = "iptable_";
        String outputTable = "optable_";
        String selectSql = null;
        String truncateSql = null;

        String runId = args[0];
        String uovId = "2";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mdt_poc","root","zim");
            stmt = con.createStatement();

            inputTable = inputTable + runId + "_" + uovId;

            System.out.println(inputTable);
            outputTable = outputTable + "test_" + uovId ;

            selectSql = String.format("select city, sum(mediacost) as cost from %s  where city != 'Jacksonville' group by city", inputTable);
            System.out.println(selectSql);
            rs = stmt.executeQuery(selectSql);

            while(rs.next()) {
                String city= rs.getString("city");
                String cost = rs.getString("cost");

                String insertSql = "insert into " + outputTable + " (city, cost, runid) values (" + "\"" + city + "\"" + " ,"
                        + cost + "," + "\"" + runId + "\"" + ")";

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
