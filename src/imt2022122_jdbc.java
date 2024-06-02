//Vansh Sinha IMT2022122
//JDBC Mini Project
import java.sql.*;
import java.util.Scanner;

public class imt2022122_jdbc {

    // Set JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";

    // Database credentials
    static final String USER = "root";
    static final String PASSWORD = "102410";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        try {
            // Register JDBC driver and open a connection
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            conn.setAutoCommit(false); // Disable auto-commit mode

            int choice;
            do {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Add Member");
                System.out.println("2. Remove Member");
                System.out.println("3. Create Team");
                System.out.println("4. Remove Team");
                System.out.println("5. Add a New Project and Associate with Team");
                System.out.println("6. Remove Project");
                System.out.println("7. Assign Existing Project to Team");
                System.out.println("8. List All Projects with Team Names");
                System.out.println("9. Add New Member and Update Team Size Accordingly");
                System.out.println("10. View Members by City");
                System.out.println("11. Change Project Duration");
                System.out.println("12. List Teams with Atleast X Members");
                System.out.println("0. Exit");
                choice = sc.nextInt();
                sc.nextLine();  // Consume newline left-over

                try {
                    switch (choice) {
                        case 1:
                            addMember(conn, sc);
                            break;
                        case 2:
                            removeMember(conn, sc);
                            break;
                        case 3:
                            createTeam(conn, sc);
                            break;
                        case 4:
                            removeTeam(conn, sc);
                            break;
                        case 5:
                            addProjectAndAssociateWithTeam(conn, sc);
                            break;
                        case 6:
                            removeProject(conn, sc);
                            break;
                        case 7:
                            assignExistingProjectToTeam(conn, sc);
                            break;
                        case 8:
                            listAllProjectsWithTeamNames(conn);
                            break;
                        case 9:
                            addMemberAndUpdateTeamSize(conn, sc);
                            break;
                        case 10:
                            viewMembersByCity(conn, sc);
                            break;
                        case 11:
                            changeProjectDuration(conn, sc);
                            break;
                        case 12:
                            listTeamsWithMoreThanXMembers(conn, sc);
                            break;
                        case 0:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    conn.commit();  // Commit the transaction
                } catch (Exception e) {
                    System.out.println("Operation failed: " + e.getMessage());
                    System.out.println("Rolling back changes");
                    conn.rollback();  // Roll back the transaction
                }
            } while (choice != 0);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("End of Code");
    }

    static void addMember(Connection conn, Scanner sc) throws SQLException {
      System.out.println("Enter Member ID, Name, and Team ID (or type 'none' for no team):");
      String member_id = sc.next();
      String member_name = sc.next();
      String team_id = sc.next();
      team_id = team_id.equals("none") ? null : team_id; // Convert 'none' to null
  
      String sql = "INSERT INTO MEMBERS (member_id, member_name, team_id) VALUES (?, ?, ?)";
      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, member_id);
          pstmt.setString(2, member_name);
          if (team_id == null) {
              pstmt.setNull(3, Types.VARCHAR);
          } else {
              pstmt.setString(3, team_id);
          }
          pstmt.executeUpdate();
          System.out.println("Member added successfully!");
      }
   }

   static void removeMember(Connection conn, Scanner sc) throws SQLException {
      System.out.println("Enter Member ID:");
      String member_id = sc.next();
      String sql = "DELETE FROM MEMBERS WHERE member_id = ?";
      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, member_id);
          int affectedRows = pstmt.executeUpdate();
          if (affectedRows > 0) {
              System.out.println("Member removed successfully!");
          } else {
              System.out.println("No member found with ID: " + member_id);
          }
      }
  }
  

    static void createTeam(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Enter Team ID, Name, Leader ID, and Team Size:");
        String team_id = sc.next();
        String team_name = sc.next();
        String leader_id = sc.next();
        int team_size = sc.nextInt();
        String sql = "INSERT INTO TEAM (team_id, team_name, team_leader_id, team_size) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team_id);
            pstmt.setString(2, team_name);
            pstmt.setString(3, leader_id);
            pstmt.setInt(4, team_size);
            pstmt.executeUpdate();
            System.out.println("Team created successfully!");
        }
    }

    static void removeTeam(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Enter Team ID:");
        String team_id = sc.next();
        String sql = "DELETE FROM TEAM WHERE team_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team_id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
               System.out.println("Team removed successfully!");
           } else {
               System.out.println("No team found with ID: " + team_id);
           }
        }
    }

    static void addProjectAndAssociateWithTeam(Connection conn, Scanner sc) throws SQLException {
      System.out.println("Enter Project ID, Project Name, Start Date, End Date, and Team ID (or type 'none' for no team):");
      String project_id = sc.next();
      String project_name = sc.next();
      String start_date = sc.next();
      String end_date = sc.next();
      String team_id = sc.next();
      team_id = team_id.equals("none") ? null : team_id; // Convert 'none' to null
  
      String sql = "INSERT INTO PROJECT (project_id, project_name, project_start_date, project_end_date, team_id) VALUES (?, ?, ?, ?, ?)";
      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, project_id);
          pstmt.setString(2, project_name);
          pstmt.setDate(3, Date.valueOf(start_date));
          pstmt.setDate(4, Date.valueOf(end_date));
          if (team_id == null) {
              pstmt.setNull(5, Types.VARCHAR); // Set the fifth parameter to NULL if team_id is 'none'
          } else {
              pstmt.setString(5, team_id);
          }
          int affectedRows = pstmt.executeUpdate();
          if (affectedRows > 0) {
              System.out.println("New project added and associated with team successfully!");
          } else {
              System.out.println("Failed to add new project.");
          }
      }
  }
  
    static void removeProject(Connection conn, Scanner sc) throws SQLException {
      System.out.println("Enter Project ID to remove:");
      String project_id = sc.next();
      String sql = "DELETE FROM PROJECT WHERE project_id = ?";
      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, project_id);
          int affectedRows = pstmt.executeUpdate();
          if (affectedRows > 0) {
              System.out.println("Project removed successfully!");
          } else {
              System.out.println("No project found with ID: " + project_id);
          }
      }
  }
  
  

  static void assignExistingProjectToTeam(Connection conn, Scanner sc) throws SQLException {
   System.out.println("Enter Project ID and Team ID to assign:");
   String project_id = sc.next();
   String team_id = sc.next();
   String sql = "UPDATE PROJECT SET team_id = ? WHERE project_id = ?";
   try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
       pstmt.setString(1, team_id);
       pstmt.setString(2, project_id);
       int affectedRows = pstmt.executeUpdate();
       if (affectedRows > 0) {
           System.out.println("Project assigned to team successfully!");
       } else {
           System.out.println("No such project exists.");
       }
      }
   }
  


   static void listAllProjectsWithTeamNames(Connection conn) throws SQLException {
      String sql = "SELECT p.project_name, t.team_name FROM PROJECT p LEFT JOIN TEAM t ON p.team_id = t.team_id";
      try (Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql)) {
          while (rs.next()) {
              String projectName = rs.getString("project_name");
              String teamName = rs.getString("team_name"); // This will be null if no team is associated
              teamName = (teamName == null) ? "No Team Assigned" : teamName;
              System.out.println("Project: " + projectName + ", Team: " + teamName);
          }
      }
  }
  

    static void addMemberAndUpdateTeamSize(Connection conn, Scanner sc) throws SQLException {
      System.out.println("Enter Member ID, Name, Team ID:");
      String member_id = sc.next();
      String member_name = sc.next();
      String team_id = sc.next();
      String insertSql = "INSERT INTO MEMBERS (member_id, member_name, team_id) VALUES (?, ?, ?)";
      String updateSql = "UPDATE TEAM SET team_size = team_size + 1 WHERE team_id = ?";
  
      try (PreparedStatement pstmtInsert = conn.prepareStatement(insertSql);
           PreparedStatement pstmtUpdate = conn.prepareStatement(updateSql)) {
          pstmtInsert.setString(1, member_id);
          pstmtInsert.setString(2, member_name);
          pstmtInsert.setString(3, team_id);
          int rowsInserted = pstmtInsert.executeUpdate();
          if (rowsInserted > 0) {
              pstmtUpdate.setString(1, team_id);
              int rowsUpdated = pstmtUpdate.executeUpdate();
              if (rowsUpdated > 0) {
                  System.out.println("New member added and team size updated successfully!");
              } else {
                  System.out.println("Failed to update team size.");
              }
          } else {
               System.out.println("No team found with ID: " + team_id);
          }
      }
  }
  

    static void viewMembersByCity(Connection conn, Scanner sc) throws SQLException {
      System.out.println("Enter City:");
      String city = sc.nextLine();
      String sql = "SELECT m.member_name, t.location_name FROM MEMBERS m JOIN TEAM_LOCATION t ON m.team_id = t.team_id WHERE t.city = ?";
      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, city);
          try (ResultSet rs = pstmt.executeQuery()) {
              boolean found = false;
              while (rs.next()) {
                  found = true;
                  System.out.println("Member: " + rs.getString("member_name") + ", Location: " + rs.getString("location_name"));
              }
              if (!found) {
                  System.out.println("No members found in this city.");
              }
          }
      }
  }
  

    static void changeProjectDuration(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Enter Project ID, new Start Date, and new End Date:");
        String project_id = sc.next();
        String start_date = sc.next();
        String end_date = sc.next();
        String sql = "UPDATE PROJECT SET project_start_date = ?, project_end_date = ? WHERE project_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(start_date));
            pstmt.setDate(2, Date.valueOf(end_date));
            pstmt.setString(3, project_id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Project duration updated successfully!");
            } else {
                System.out.println("No such project exists.");
            }
        }
    }

    static void listTeamsWithMoreThanXMembers(Connection conn, Scanner sc) throws SQLException {
      System.out.println("Enter minimum number of members:");
      int minMembers = sc.nextInt();
      String sql = "SELECT team_name, team_size FROM TEAM WHERE team_size >= ?";
      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setInt(1, minMembers);
          try (ResultSet rs = pstmt.executeQuery()) {
              if (!rs.next()) {
                  System.out.println("No teams found with atleast " + minMembers + " members.");
                  return;
              }
              do {
                  System.out.println("Team: " + rs.getString("team_name") + ", Size: " + rs.getInt("team_size"));
              } while (rs.next());
          }
      }
  }
  
}
