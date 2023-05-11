CREATE TABLE CODES (
  ID INT PRIMARY KEY,
  CODE VARCHAR(55),
  Latitude NUMERIC,
  Longitude NUMERIC
) AS SELECT * FROM CSVREAD ('classpath:postcodes-coordinates-NL.csv');