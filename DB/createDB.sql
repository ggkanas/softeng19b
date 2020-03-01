CREATE DATABASE IF NOT EXISTS softeng19b;
USE softeng19b;

SOURCE MapCode.sql;
SOURCE AllocatedEICDetail.sql;
SOURCE ProductionType.sql;
SOURCE AreaTypeCode.sql;
SOURCE ResolutionCode.sql;
SOURCE ActualTotalLoad.sql;
SOURCE DayAheadTotalLoadForecast.sql;
SOURCE AggregatedGenerationPerType.sql;
SOURCE User.sql;

CREATE USER 'softeng'@'localhost' IDENTIFIED BY 'softeng19B!';
GRANT ALL PRIVILEGES ON * . * TO 'softeng'@'localhost';

INSERT INTO User
(Username, Email, Password, RequestsPerDayQuota, Period, RemainingRequests)
VALUES('admin', null, 'nimda', -1, CURRENT_TIMESTAMP(), -1);
