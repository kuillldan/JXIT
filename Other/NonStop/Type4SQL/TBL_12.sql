(
SELECT 
 A.COL_12_001
FROM
 TBL_12 A
WHERE
 A.COL_12_002 IN (?文字列①?, ?文字列②?, ?文字列③?, ?文字列④?)
AND
 A.COL_12_106 = ?文字列⑤?
)
UNION ALL
(
SELECT
 A.COL_12_001
FROM
 TBL_12 A
WHERE
 A.COL_12_002 IN (?文字列①?, ?文字列②?, ?文字列③?, ?文字列④?)
AND
 A.COL_12_108 = ?文字列⑤?
)
UNION ALL
(
SELECT
 A.COL_12_001
FROM
 TBL_12 A
WHERE
 A.COL_12_002 IN (?文字列①?, ?文字列②?, ?文字列③?, ?文字列④?)
AND
 A.COL_12_107 = ?文字列⑤?
)
