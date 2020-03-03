CREATE DATABASE IF NOT EXISTS softeng19b;
USE softeng19b;

SOURCE MapCode.sql;
SOURCE MapCodeRecords.sql;
SOURCE AllocatedEICDetail.sql;
SOURCE ProductionType.sql;
SOURCE ProductionTypeRecords.sql;
SOURCE AreaTypeCode.sql;
SOURCE AreaTypeCodeRecords.sql;
SOURCE ResolutionCode.sql;
SOURCE ResolutionCodeRecords.sql;
SOURCE ActualTotalLoad.sql;
SOURCE DayAheadTotalLoadForecast.sql;
SOURCE AggregatedGenerationPerType.sql;
SOURCE User.sql;

CREATE USER 'softeng'@'localhost' IDENTIFIED BY 'softeng19B!';
GRANT ALL PRIVILEGES ON * . * TO 'softeng'@'localhost';

INSERT INTO User
(Username, Email, Password, RequestsPerDayQuota, Period, RemainingRequests)
VALUES('admin', null, '321nimda', -1, CURRENT_TIMESTAMP(), -1);
