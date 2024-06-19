import java.sql.*; //import package

public class DemoJDBC {
    public static void main(String[] args) throws Exception {
        /*
            Steps involved in JDBC:-
            import package
            load and register
            create connection
            execute statement
            process the results
            close
        */

        String url = "jdbc:postgresql://localhost:5432/postgres"; //format:- java will be connecting with what : database (postgresql/mysql/oracle) ://localhost:port number/database name
        String uname = "postgres";
        String password = "2409";
        //String sql = "select sname from student where sid = 1"; // create statement
        String sql = "select * from student"; // create statement


        Class.forName("org.postgresql.Driver"); // load and register

        Connection con = DriverManager.getConnection(url, uname, password); //create connection

        System.out.println("Connection Established");

        // create statement
        Statement st = con.createStatement();

        // execute statement
        ResultSet rs = st.executeQuery(sql);

        // process the result
        //Print single entry:-
        //rs.next();// since the pointer is before the 1st entry so, we move pointer to 1st entry
        //String name = rs.getString("sname");
        //System.out.println("Name: "+name);

        //Print entire table
        while (rs.next()) {
            System.out.print(rs.getInt(1) + " - ");
            System.out.print(rs.getString(2) + " - ");
            System.out.println(rs.getInt(3));
        }

        //insert entry into database
        String sql1 = "insert into student values (5, 'Lavanya', 88)";
        boolean status = st.execute(sql1);
        System.out.println(status);
        System.out.println("Row inserted successfully");

        //update data
        String sql2 = "update student set sname = 'Brownie' where sid = 5";
        st.execute(sql2);
        System.out.println("Change updated successfully");

        //delete data
        String sql3 = "delete from student where sid = 5";
        st.execute(sql3);
        System.out.println("Row deleted successfully");

        //prepared statement
        int sid = 6;
        String sname = "Seema";
        int marks = 98;
        //String sql4 = "insert into student values (" + sid + ", '" + sname + "', " + marks + ")"; // wrong method and can create SQL injections
        String sql4 = "insert into student values (?, ?, ?)"; // precompiled query for cache purpose
        PreparedStatement ps = con.prepareStatement(sql4);
        ps.setInt(1, sid);
        ps.setString(2, sname);
        ps.setInt(3, marks);
        ps.execute();

        // close
        con.close();
        System.out.println("Connection closed");
    }
}
