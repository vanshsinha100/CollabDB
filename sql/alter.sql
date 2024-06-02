-- Add foreign key constraints to the 'TEAM' table
ALTER TABLE TEAM
ADD CONSTRAINT fk_team_members
FOREIGN KEY (team_leader_id) REFERENCES MEMBERS(member_id)
ON DELETE SET NULL;

-- Add foreign key constraints to the 'PROJECT' table
ALTER TABLE PROJECT
ADD CONSTRAINT fk_project_team
FOREIGN KEY (team_id) REFERENCES TEAM(team_id)
ON DELETE SET NULL;

-- Add foreign key constraints to the 'MEMBERS' table
ALTER TABLE MEMBERS
ADD CONSTRAINT fk_members_team
FOREIGN KEY (team_id) REFERENCES TEAM(team_id)
ON DELETE SET NULL;

-- Add foreign key constraints to the 'TEAM_LOCATION' table
ALTER TABLE TEAM_LOCATION
ADD CONSTRAINT fk_team_location_team
FOREIGN KEY (team_id) REFERENCES TEAM(team_id)
ON DELETE CASCADE;
