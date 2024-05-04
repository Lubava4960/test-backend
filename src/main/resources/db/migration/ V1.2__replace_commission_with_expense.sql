update budget set type = 'Расход' where type = 'Комиссия';
CREATE TABLE Author (
    id SERIAL PRIMARY KEY,
    full_name TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
alter table budget add column author_id integer;
ALTER TABLE budget
ADD CONSTRAINT fk_budget_author_id
FOREIGN KEY (author_id)
REFERENCES author(id);
