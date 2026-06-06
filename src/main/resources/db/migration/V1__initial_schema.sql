CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- =========================
-- USERS
-- =========================
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    clerk_user_id VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    name VARCHAR(255),
    image_url TEXT,
    industry VARCHAR(255),
    bio TEXT,
    experience INTEGER,
    skills JSONB,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_clerk_user_id ON users(clerk_user_id);
CREATE INDEX idx_users_email ON users(email);

-- =========================
-- RESUMES (1:1 with user)
-- =========================
CREATE TABLE resumes (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    content TEXT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_resume_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_resume_user ON resumes(user_id);

-- =========================
-- ATS ANALYSIS
-- =========================
CREATE TABLE ats_analysis (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL UNIQUE,

    score INTEGER,
    keywords JSONB,
    missing_keywords JSONB,
    suggestions JSONB,
    raw_response JSONB,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_ats_resume
        FOREIGN KEY(resume_id)
        REFERENCES resumes(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_ats_resume ON ats_analysis(resume_id);
CREATE INDEX idx_ats_keywords ON ats_analysis USING GIN (keywords);

-- =========================
-- COVER LETTERS
-- =========================
CREATE TABLE cover_letters (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,

    company_name VARCHAR(255),
    job_title VARCHAR(255),
    job_description TEXT,
    content TEXT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cover_letter_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_cover_letter_user ON cover_letters(user_id);
CREATE INDEX idx_cover_letter_company ON cover_letters(LOWER(company_name));

-- =========================
-- ASSESSMENTS
-- =========================
CREATE TABLE assessments (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,

    quiz_score INTEGER,
    questions JSONB,
    answers JSONB,
    improvement_tip TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_assessment_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_assessment_user ON assessments(user_id);
CREATE INDEX idx_assessment_created_at ON assessments(created_at);
CREATE INDEX idx_assessment_questions ON assessments USING GIN (questions);

-- =========================
-- INDUSTRY INSIGHTS (1:1 per user)
-- =========================
CREATE TABLE industry_insights (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,

    industry VARCHAR(255),
    salary_ranges JSONB,
    growth_rate VARCHAR(255),
    demand_level VARCHAR(255),
    top_skills JSONB,
    market_outlook TEXT,
    key_trends JSONB,
    recommended_skills JSONB,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_industry_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_industry_user ON industry_insights(user_id);
CREATE INDEX idx_industry_name ON industry_insights(industry);