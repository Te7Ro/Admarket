DO $$ BEGIN
CREATE TYPE user_role AS ENUM ('COMPANY', 'CREATOR', 'ADMIN');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE user_status AS ENUM ('ACTIVE', 'SUSPENDED');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE platform_type AS ENUM ('YOUTUBE', 'INSTAGRAM', 'TIKTOK', 'TELEGRAM', 'TWITCH', 'OTHER');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE offer_status AS ENUM ('DRAFT', 'ACTIVE', 'PAUSED', 'CLOSED');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE moderation_status AS ENUM ('PENDING', 'APPROVED', 'REJECTED');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE risk_label AS ENUM ('LOW', 'MEDIUM', 'HIGH');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE conversation_type AS ENUM ('GENERAL', 'OFFER');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE message_type AS ENUM ('TEXT', 'SYSTEM');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE attachment_type AS ENUM ('IMAGE', 'VIDEO', 'FILE', 'LINK', 'OTHER');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

DO $$ BEGIN
CREATE TYPE chat_sender AS ENUM ('USER', 'BOT', 'OPERATOR');
EXCEPTION WHEN duplicate_object THEN NULL; END $$;

CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS trigger AS $$
BEGIN
  NEW.updated_at = now();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role user_role NOT NULL,
    status user_status NOT NULL DEFAULT 'SUSPENDED'
    );

DROP TRIGGER IF EXISTS trg_users_updated_at ON users;
CREATE TRIGGER trg_users_updated_at
    BEFORE UPDATE ON users
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS categories (
                                          id BIGSERIAL PRIMARY KEY,
                                          created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    name TEXT NOT NULL UNIQUE
    );

DROP TRIGGER IF EXISTS trg_categories_updated_at ON categories;
CREATE TRIGGER trg_categories_updated_at
    BEFORE UPDATE ON categories
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS countries (
                                         id BIGSERIAL PRIMARY KEY,
                                         created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    name TEXT NOT NULL,
    code TEXT NOT NULL UNIQUE
    );

DROP TRIGGER IF EXISTS trg_countries_updated_at ON countries;
CREATE TRIGGER trg_countries_updated_at
    BEFORE UPDATE ON countries
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS industries (
                                          id BIGSERIAL PRIMARY KEY,
                                          created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    name TEXT NOT NULL UNIQUE
    );

DROP TRIGGER IF EXISTS trg_industries_updated_at ON industries;
CREATE TRIGGER trg_industries_updated_at
    BEFORE UPDATE ON industries
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS company_profiles (
                                                id BIGSERIAL PRIMARY KEY,
                                                created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    user_id BIGINT NOT NULL UNIQUE,
    company_name TEXT NOT NULL,
    industry_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    website_url TEXT,
    country_id BIGINT NOT NULL,
    min_budget NUMERIC(12,2) NOT NULL,
    max_budget DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_company_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_company_industry FOREIGN KEY (industry_id) REFERENCES industries(id),
    CONSTRAINT fk_company_country FOREIGN KEY (country_id) REFERENCES countries(id)
    );

CREATE INDEX IF NOT EXISTS ix_company_profiles_industry_id ON company_profiles(industry_id);
CREATE INDEX IF NOT EXISTS ix_company_profiles_country_id ON company_profiles(country_id);

DROP TRIGGER IF EXISTS trg_company_profiles_updated_at ON company_profiles;
CREATE TRIGGER trg_company_profiles_updated_at
    BEFORE UPDATE ON company_profiles
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS company_preferred_categories (
                                                            id BIGSERIAL PRIMARY KEY,
                                                            created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    company_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_company_pref_company FOREIGN KEY (company_id) REFERENCES company_profiles(id) ON DELETE CASCADE,
    CONSTRAINT fk_company_pref_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT ux_company_pref UNIQUE (company_id, category_id)
    );

CREATE INDEX IF NOT EXISTS ix_company_pref_company_id ON company_preferred_categories(company_id);
CREATE INDEX IF NOT EXISTS ix_company_pref_category_id ON company_preferred_categories(category_id);

DROP TRIGGER IF EXISTS trg_company_preferred_categories_updated_at ON company_preferred_categories;
CREATE TRIGGER trg_company_preferred_categories_updated_at
    BEFORE UPDATE ON company_preferred_categories
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS creator_profiles (
                                                id BIGSERIAL PRIMARY KEY,
                                                created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    user_id BIGINT NOT NULL UNIQUE,
    display_name TEXT NOT NULL,
    bio TEXT,
    primary_category_id BIGINT NOT NULL,
    followers_count INT NOT NULL,
    avg_views INT NOT NULL,
    engagement_rate NUMERIC(5,2) NOT NULL CHECK (engagement_rate BETWEEN 0 AND 100),
    contact_email TEXT,
    CONSTRAINT fk_creator_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_creator_category FOREIGN KEY (primary_category_id) REFERENCES categories(id)
    );

CREATE INDEX IF NOT EXISTS ix_creator_profiles_primary_category_id ON creator_profiles(primary_category_id);

DROP TRIGGER IF EXISTS trg_creator_profiles_updated_at ON creator_profiles;
CREATE TRIGGER trg_creator_profiles_updated_at
    BEFORE UPDATE ON creator_profiles
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS creator_platforms (
                                                 id BIGSERIAL PRIMARY KEY,
                                                 created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    creator_id BIGINT NOT NULL,
    platform platform_type NOT NULL,
    profile_url TEXT NOT NULL,
    followers_count INT,
    avg_views INT,
    CONSTRAINT fk_creator_platform FOREIGN KEY (creator_id) REFERENCES creator_profiles(id) ON DELETE CASCADE,
    CONSTRAINT ux_creator_platform UNIQUE (creator_id, platform)
    );

CREATE INDEX IF NOT EXISTS ix_creator_platforms_creator_id ON creator_platforms(creator_id);

DROP TRIGGER IF EXISTS trg_creator_platforms_updated_at ON creator_platforms;
CREATE TRIGGER trg_creator_platforms_updated_at
    BEFORE UPDATE ON creator_platforms
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS creator_audience_age (
                                                    id BIGSERIAL PRIMARY KEY,
                                                    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    creator_id BIGINT NOT NULL,
    age_begin INT NOT NULL,
    age_end INT NOT NULL,
    percentage NUMERIC(5,2) NOT NULL CHECK (percentage BETWEEN 0 AND 100),
    CONSTRAINT fk_creator_audience_age_creator FOREIGN KEY (creator_id) REFERENCES creator_profiles(id) ON DELETE CASCADE,
    CONSTRAINT chk_creator_audience_age_range CHECK (age_end >= age_begin)
    );

CREATE INDEX IF NOT EXISTS ix_creator_audience_age_creator_id ON creator_audience_age(creator_id);

DROP TRIGGER IF EXISTS trg_creator_audience_age_updated_at ON creator_audience_age;
CREATE TRIGGER trg_creator_audience_age_updated_at
    BEFORE UPDATE ON creator_audience_age
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS creator_audience_geo (
                                                    id BIGSERIAL PRIMARY KEY,
                                                    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    creator_id BIGINT NOT NULL,
    country_id BIGINT NOT NULL,
    percentage NUMERIC(5,2) NOT NULL CHECK (percentage BETWEEN 0 AND 100),
    CONSTRAINT fk_creator_audience_geo_creator FOREIGN KEY (creator_id) REFERENCES creator_profiles(id) ON DELETE CASCADE,
    CONSTRAINT fk_creator_audience_geo_country FOREIGN KEY (country_id) REFERENCES countries(id),
    CONSTRAINT ux_creator_audience_geo UNIQUE (creator_id, country_id)
    );

CREATE INDEX IF NOT EXISTS ix_creator_audience_geo_creator_id ON creator_audience_geo(creator_id);
CREATE INDEX IF NOT EXISTS ix_creator_audience_geo_country_id ON creator_audience_geo(country_id);

DROP TRIGGER IF EXISTS trg_creator_audience_geo_updated_at ON creator_audience_geo;
CREATE TRIGGER trg_creator_audience_geo_updated_at
    BEFORE UPDATE ON creator_audience_geo
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS offers (
                                      id BIGSERIAL PRIMARY KEY,
                                      created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    company_id BIGINT NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    category_id BIGINT NOT NULL,
    budget DOUBLE PRECISION NOT NULL,
    currency CHAR(3) NOT NULL,
    target_min_age INT NOT NULL,
    target_max_age INT NOT NULL,
    campaign_start_date DATE,
    campaign_end_date DATE,
    status offer_status NOT NULL,
    CONSTRAINT fk_offer_company FOREIGN KEY (company_id) REFERENCES company_profiles(id) ON DELETE CASCADE,
    CONSTRAINT fk_offer_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT chk_offer_age_range CHECK (target_max_age >= target_min_age),
    CONSTRAINT chk_offer_campaign_dates CHECK (campaign_start_date IS NULL OR campaign_end_date IS NULL OR campaign_end_date >= campaign_start_date)
    );

CREATE INDEX IF NOT EXISTS ix_offer_company ON offers(company_id);
CREATE INDEX IF NOT EXISTS ix_offer_category ON offers(category_id);

DROP TRIGGER IF EXISTS trg_offers_updated_at ON offers;
CREATE TRIGGER trg_offers_updated_at
    BEFORE UPDATE ON offers
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS offer_target_countries (
                                                      id BIGSERIAL PRIMARY KEY,
                                                      created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    offer_id BIGINT NOT NULL,
    country_id BIGINT NOT NULL,
    CONSTRAINT fk_offer_target_offer FOREIGN KEY (offer_id) REFERENCES offers(id) ON DELETE CASCADE,
    CONSTRAINT fk_offer_target_country FOREIGN KEY (country_id) REFERENCES countries(id),
    CONSTRAINT ux_offer_target UNIQUE (offer_id, country_id)
    );

CREATE INDEX IF NOT EXISTS ix_offer_target_offer ON offer_target_countries(offer_id);
CREATE INDEX IF NOT EXISTS ix_offer_target_country ON offer_target_countries(country_id);

DROP TRIGGER IF EXISTS trg_offer_target_countries_updated_at ON offer_target_countries;
CREATE TRIGGER trg_offer_target_countries_updated_at
    BEFORE UPDATE ON offer_target_countries
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS offer_risk_analysis (
                                                   id BIGSERIAL PRIMARY KEY,
                                                   created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    offer_id BIGINT NOT NULL UNIQUE,
    risk_score NUMERIC(4,3) NOT NULL CHECK (risk_score >= 0 AND risk_score <= 1),
    risk_label risk_label NOT NULL,
    moderation_status moderation_status NOT NULL,
    reasons JSONB NOT NULL,
    CONSTRAINT fk_offer_risk_offer FOREIGN KEY (offer_id) REFERENCES offers(id) ON DELETE CASCADE
    );

CREATE INDEX IF NOT EXISTS ix_offer_risk_offer ON offer_risk_analysis(offer_id);

DROP TRIGGER IF EXISTS trg_offer_risk_analysis_updated_at ON offer_risk_analysis;
CREATE TRIGGER trg_offer_risk_analysis_updated_at
    BEFORE UPDATE ON offer_risk_analysis
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS match_results (
                                             id BIGSERIAL PRIMARY KEY,
                                             created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    offer_id BIGINT NOT NULL,
    creator_id BIGINT NOT NULL,
    similarity_score NUMERIC(5,4) NOT NULL,
    performance_score NUMERIC(5,4) NOT NULL,
    risk_penalty NUMERIC(5,4) NOT NULL,
    final_score NUMERIC(5,4) NOT NULL,
    explanation TEXT NOT NULL,
    breakdown JSONB NOT NULL,
    CONSTRAINT fk_match_offer FOREIGN KEY (offer_id) REFERENCES offers(id) ON DELETE CASCADE,
    CONSTRAINT fk_match_creator FOREIGN KEY (creator_id) REFERENCES creator_profiles(id) ON DELETE CASCADE,
    CONSTRAINT ux_match UNIQUE (offer_id, creator_id)
    );

CREATE INDEX IF NOT EXISTS ix_match_offer ON match_results(offer_id);
CREATE INDEX IF NOT EXISTS ix_match_creator ON match_results(creator_id);

DROP TRIGGER IF EXISTS trg_match_results_updated_at ON match_results;
CREATE TRIGGER trg_match_results_updated_at
    BEFORE UPDATE ON match_results
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS conversations (
                                             id BIGSERIAL PRIMARY KEY,
                                             created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    type conversation_type NOT NULL,
    offer_id BIGINT,
    created_by_user_id BIGINT,
    last_message_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT fk_conversation_offer FOREIGN KEY (offer_id) REFERENCES offers(id) ON DELETE SET NULL,
    CONSTRAINT fk_conversation_created_by FOREIGN KEY (created_by_user_id) REFERENCES users(id) ON DELETE SET NULL
    );

CREATE INDEX IF NOT EXISTS ix_conversations_offer ON conversations(offer_id);
CREATE INDEX IF NOT EXISTS ix_conversations_created_by ON conversations(created_by_user_id);

DROP TRIGGER IF EXISTS trg_conversations_updated_at ON conversations;
CREATE TRIGGER trg_conversations_updated_at
    BEFORE UPDATE ON conversations
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS conversation_participants (
                                                         id BIGSERIAL PRIMARY KEY,
                                                         created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    conversation_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    role_in_conversation user_role NOT NULL,
    joined_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    is_muted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_participant_conversation FOREIGN KEY (conversation_id) REFERENCES conversations(id) ON DELETE CASCADE,
    CONSTRAINT fk_participant_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT ux_participant UNIQUE (conversation_id, user_id)
    );

CREATE INDEX IF NOT EXISTS ix_participants_conversation ON conversation_participants(conversation_id);
CREATE INDEX IF NOT EXISTS ix_participants_user ON conversation_participants(user_id);

DROP TRIGGER IF EXISTS trg_conversation_participants_updated_at ON conversation_participants;
CREATE TRIGGER trg_conversation_participants_updated_at
    BEFORE UPDATE ON conversation_participants
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS messages (
                                        id BIGSERIAL PRIMARY KEY,
                                        created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    conversation_id BIGINT NOT NULL,
    sender_user_id BIGINT NOT NULL,
    message_type message_type NOT NULL,
    body TEXT NOT NULL,
    read_at TIMESTAMPTZ,
    CONSTRAINT fk_message_conversation FOREIGN KEY (conversation_id) REFERENCES conversations(id) ON DELETE CASCADE,
    CONSTRAINT fk_message_sender FOREIGN KEY (sender_user_id) REFERENCES users(id) ON DELETE CASCADE
    );

CREATE INDEX IF NOT EXISTS ix_messages_conversation ON messages(conversation_id);
CREATE INDEX IF NOT EXISTS ix_messages_sender ON messages(sender_user_id);

DROP TRIGGER IF EXISTS trg_messages_updated_at ON messages;
CREATE TRIGGER trg_messages_updated_at
    BEFORE UPDATE ON messages
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS message_attachments (
                                                   id BIGSERIAL PRIMARY KEY,
                                                   created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    message_id BIGINT NOT NULL,
    type attachment_type NOT NULL,
    url VARCHAR(1024),
    file_name TEXT,
    CONSTRAINT fk_attachment_message FOREIGN KEY (message_id) REFERENCES messages(id) ON DELETE CASCADE
    );

CREATE INDEX IF NOT EXISTS ix_message_attachments_message ON message_attachments(message_id);

DROP TRIGGER IF EXISTS trg_message_attachments_updated_at ON message_attachments;
CREATE TRIGGER trg_message_attachments_updated_at
    BEFORE UPDATE ON message_attachments
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS chat_widget_session (
                                                   id BIGSERIAL PRIMARY KEY,
                                                   created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    user_id BIGINT NOT NULL,
    started_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    ended_at TIMESTAMPTZ,
    context JSONB NOT NULL,
    CONSTRAINT fk_widget_session_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );

CREATE INDEX IF NOT EXISTS ix_widget_session_user ON chat_widget_session(user_id);

DROP TRIGGER IF EXISTS trg_chat_widget_session_updated_at ON chat_widget_session;
CREATE TRIGGER trg_chat_widget_session_updated_at
    BEFORE UPDATE ON chat_widget_session
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TABLE IF NOT EXISTS chat_widget_messages (
                                                    id BIGSERIAL PRIMARY KEY,
                                                    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    session_id BIGINT NOT NULL,
    sender chat_sender NOT NULL,
    text TEXT NOT NULL,
    intent VARCHAR(64),
    metadata JSONB NOT NULL,
    CONSTRAINT fk_widget_message_session FOREIGN KEY (session_id) REFERENCES chat_widget_session(id) ON DELETE CASCADE
    );

CREATE INDEX IF NOT EXISTS ix_widget_messages_session ON chat_widget_messages(session_id);

DROP TRIGGER IF EXISTS trg_chat_widget_messages_updated_at ON chat_widget_messages;
CREATE TRIGGER trg_chat_widget_messages_updated_at
    BEFORE UPDATE ON chat_widget_messages
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();
