DROP TABLE IF EXISTS notes CASCADE;

DROP SEQUENCE IF EXISTS seq_note_id;

CREATE SEQUENCE seq_note_id;


CREATE TABLE notes
(
	id INT PRIMARY KEY DEFAULT NEXTVAL('seq_note_id'),
	published TIMESTAMP NOT NULL,
	title TEXT,
	content TEXT NOT NULL,
	tags TEXT
);


-- test data


INSERT INTO notes(id, published, title, content, tags) VALUES (1, '2019-01-01', 'this is an answer', 'uahonaut nohotanuh', 'ttt');
INSERT INTO notes(id, published, title, content, tags) VALUES (2, '2019-01-01', 'this is another answer', 'nauosua soaosu', 'mtm');
INSERT INTO notes(id, published, title, content, tags) VALUES (3, '2019-01-01', 'this is yet another question', 'this is yet another answer', 'hbhdtdhd');
