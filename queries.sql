## Part 1: Test it with SQL
id int PK
employer varchar(255)
name varchar(255)
skills varchar(255)

## Part 2: Test it with SQL
SELECT name
FROM employer
WHERE (location = "Saint Louis City")

## Part 3: Test it with SQL
DROP TABLE job

## Part 4: Test it with SQL
SELECT skill.name, skill.description
FROM skill
INNER JOIN job_skills ON skill.id = job_skills.skills_id
INNER JOIN job ON job.id=job_skills.jobs_id
WHERE job.id IS NOT NULL
ORDER BY name;