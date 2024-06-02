-- Update foreign keys in the 'TEAM' table
UPDATE TEAM
SET team_leader_id = "M1"
WHERE team_id = 'T1';

UPDATE TEAM
SET team_leader_id = "M4"
WHERE team_id = 'T2';

UPDATE TEAM
SET team_leader_id = "M8"
WHERE team_id = 'T3';

-- Update foreign keys in the 'PROJECT' table
UPDATE PROJECT
SET team_id = 'T1'
WHERE project_id = 'P1';

UPDATE PROJECT
SET team_id = 'T2'
WHERE project_id = 'P2';

UPDATE PROJECT
SET team_id = 'T3'
WHERE project_id = 'P3';

-- Update foreign keys in the 'TEAM_LOCATION' table
UPDATE TEAM_LOCATION
SET team_id = 'T1'
WHERE location_id = 1;

UPDATE TEAM_LOCATION
SET team_id = 'T2'
WHERE location_id = 2;

UPDATE TEAM_LOCATION
SET team_id = 'T3'
WHERE location_id = 3;

-- Update foreign keys in the 'MEMBERS' table
UPDATE MEMBERS
SET team_id = 'T1'
WHERE member_id IN ("M1", "M2");

UPDATE MEMBERS
SET team_id = 'T2'
WHERE member_id IN ("M3", "M4", "M5");

UPDATE MEMBERS
SET team_id = 'T3'
WHERE member_id IN ("M6", "M7", "M8", "M9");
