-- 코드를 입력하세요
SELECT MEMBER_NAME, REVIEW_TEXT
       , DATE_FORMAT(REVIEW_DATE, '%Y-%m-%d') AS "REVIEW_DATE"
FROM REST_REVIEW
INNER JOIN MEMBER_PROFILE
ON REST_REVIEW.MEMBER_ID = MEMBER_PROFILE.MEMBER_ID
WHERE REST_REVIEW.MEMBER_ID = (SELECT MEMBER_ID
                     FROM REST_REVIEW
                     GROUP BY MEMBER_ID
                     ORDER BY COUNT(REVIEW_SCORE) DESC
                     LIMIT 1)
ORDER BY REVIEW_DATE, REVIEW_TEXT