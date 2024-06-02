-- Insert sample data into the 'TEAM' table
INSERT INTO TEAM (team_id, team_name, team_leader_id, team_size) VALUES
('T1', 'Team Alpha', NULL, 2),
('T2', 'Team Beta', NULL, 3),
('T3', 'Team Omega', NULL, 4);

-- Insert sample data into the 'PROJECT' table
INSERT INTO PROJECT (project_id, project_name, project_start_date, project_end_date, team_id) VALUES
('P1', 'Project Genesis', '2024-01-01', '2024-06-30', NULL),
('P2', 'Project Horizon', '2024-02-01', '2024-08-31', NULL),
('P3', 'Project Zenith', '2024-03-01', '2024-09-30', NULL);

-- Insert sample data into the 'MEMBERS' table
INSERT INTO MEMBERS (member_id, member_name, team_id) VALUES
("M1", 'Alice Anderson', NULL),
("M2", 'Bob Barker', NULL),
("M3", 'Charlie Cooper', NULL),
("M4", 'David Davis', NULL),
("M5", 'Emily Evans', NULL),
("M6", 'Frank Foster', NULL),
("M7", 'Grace Garcia', NULL),
("M8", 'Henry Harrison', NULL),
("M9", 'Isabella Ingram', NULL);

-- Insert sample data into the 'TEAM_LOCATION' table
INSERT INTO TEAM_LOCATION (location_id, location_name, city, team_id) VALUES
(1, 'Electronic City Phase 1', 'Bangalore', NULL),
(2, 'Electronic City Phase 2', 'Bangalore', NULL),
(3, 'Gomti Nagar', 'Lucknow', NULL);