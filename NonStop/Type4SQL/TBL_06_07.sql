﻿SELECT
 別名①.*,
 別名②.COL_07_002
FROM
 TBL_06 AS 別名①,
 TBL_07 AS 別名②
WHERE
 別名①.COL_06_001 = ?
AND
 別名①.COL_06_002
 =
 別名②.COL_07_001
ORDER BY
 別名①.COL_06_002 ASC,
 別名①.COL_06_006 DESC