DROP TABLE MYMVCBOARD;

CREATE TABLE MYMVCBOARD(
	SEQ INT AUTO_INCREMENT PRIMARY KEY,
	WRITER VARCHAR(100) NOT NULL,
	TITLE VARCHAR(1000) NOT NULL,
	CONTNET VARCHAR(4000) NOT NULL,
	REGDATE DATE NOT NULL
);

INSERT INTO MYMVCBOARD VALUES(NULL, '관리자', '테스트 중', '테스트 중 입니다.', NOW());

SELECT * FROM MYMVCBOARD;