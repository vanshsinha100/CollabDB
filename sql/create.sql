-- Creating Database jdbc
CREATE DATABASE jdbc;
USE jdbc;

-- Create the 'team' table
CREATE TABLE TEAM (
    team_id VARCHAR(100),
    team_name VARCHAR(100),
    team_leader_id VARCHAR(100),
    team_size INT,
    CONSTRAINT TEAM_pk PRIMARY KEY (team_id)
    -- FOREIGN KEY (team_leader_id) REFERENCES MEMBERS(member_id)
);

-- Create the 'project' table
CREATE TABLE PROJECT (
    project_id VARCHAR(100),
    project_name VARCHAR(100),
    project_start_date DATE,
    project_end_date DATE,
    team_id VARCHAR(100),
    CONSTRAINT PROJECT_pk PRIMARY KEY (project_id)
    -- FOREIGN KEY (team_id) REFERENCES team(team_id)
);

-- Create the 'members' table
CREATE TABLE MEMBERS (
    member_id VARCHAR(100),
    member_name VARCHAR(100),
    team_id VARCHAR(100),
    CONSTRAINT MEMBERS_pk PRIMARY KEY (member_id)
    -- FOREIGN KEY (team_id) REFERENCES team(team_id)
);

-- Create the 'team_location' table
CREATE TABLE TEAM_LOCATION (
    location_id INT,
    location_name VARCHAR(100),
    city VARCHAR(100),
    team_id VARCHAR(100),
    CONSTRAINT TEAM_LOCATION_pk PRIMARY KEY (location_id)
    -- FOREIGN KEY (team_id) REFERENCES team(team_id)
);