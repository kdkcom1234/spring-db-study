INSERT INTO `post` (`title`, `content`, profile_id)
VALUES
  ('제목 1', '내용 1', 1),
  ('제목 2', '내용 2', 1),
  ('제목 3', '내용 3', 1),
  ('제목 4', '내용 4', 1),
  ('제목 5', '내용 5', 1),
  ('제목 6', '내용 6', 1),
  ('제목 7', '내용 7', 1),
  ('제목 8', '내용 8', 1),
  ('제목 9', '내용 9', 1),
  ('제목 10', '내용 10', 1);
select * from post;
select * from post where id = 10;
select * from post_comment;



INSERT INTO `post_comment` (`post_id`, `comment`, profile_id) VALUES
  (FLOOR(RAND() * 10) + 1, 'This is a great post.', 1),
  (FLOOR(RAND() * 10) + 1, 'I totally agree with your points.', 1),
  (FLOOR(RAND() * 10) + 1, 'Thanks for sharing this information.', 1),
  (FLOOR(RAND() * 10) + 1, 'I have a different perspective on this.', 1),
  (FLOOR(RAND() * 10) + 1, 'Can you provide more examples related to this?', 1),
  (FLOOR(RAND() * 10) + 1, 'I found a typo in the post.', 1),
  (FLOOR(RAND() * 10) + 1, 'Your insights are really valuable.', 1),
  (FLOOR(RAND() * 10) + 1, 'I''m looking forward to your next post.', 1),
  (FLOOR(RAND() * 10) + 1, 'This post needs more references.', 1),
  (FLOOR(RAND() * 10) + 1, 'I''m glad I stumbled upon this.', 1),
  (FLOOR(RAND() * 10) + 1, 'I have a question regarding point #2.', 1),
  (FLOOR(RAND() * 10) + 1, 'You explained this complex topic very well.', 1),
  (FLOOR(RAND() * 10) + 1, 'I''m sharing this with my friends for sure.', 1),
  (FLOOR(RAND() * 10) + 1, 'Looking forward to deeper insights in the future.', 1),
  (FLOOR(RAND() * 10) + 1, 'I''m bookmarking this page.', 1),
  (FLOOR(RAND() * 10) + 1, 'I wish there were more examples provided.', 1),
  (FLOOR(RAND() * 10) + 1, 'This post is hard to understand.', 1),
  (FLOOR(RAND() * 10) + 1, 'Your points are well-researched.', 1),
  (FLOOR(RAND() * 10) + 1, 'I''m sharing this on social media.', 1),
  (FLOOR(RAND() * 10) + 1, 'Can you recommend more resources on this topic?', 1),
  (FLOOR(RAND() * 10) + 1, 'I have a similar experience to share.', 1),
  (FLOOR(RAND() * 10) + 1, 'I disagree with some parts of the post.', 1),
  (FLOOR(RAND() * 10) + 1, 'Your writing style is engaging.', 1),
  (FLOOR(RAND() * 10) + 1, 'I''m looking forward to more real-life examples.', 1),
  (FLOOR(RAND() * 10) + 1, 'This post changed my viewpoint.', 1),
  (FLOOR(RAND() * 10) + 1, 'I''m recommending this to my colleagues.', 1),
  (FLOOR(RAND() * 10) + 1, 'Your analysis is spot-on.', 1),
  (FLOOR(RAND() * 10) + 1, 'I''m eager to learn more from you.', 1),
  (FLOOR(RAND() * 10) + 1, 'This post is a bit too technical for me.', 1),
  (FLOOR(RAND() * 10) + 1, 'I appreciate the effort you put into this content.', 1);


select p.id, p.title, p.created_date, count(c.id) as 'comment_count'
from post p 
	left join post_comment c on p.id = c.post_id
group by p.id, p.title, p.created_date
order by p.id desc;

select * from profile;
select * from identity;

-- identity 테이블
insert into identity(secret, username)
values('$2a$12$pHuFp9mBfY5OrT36H19JF.nZM0icwZvYwmyyNhTaY7XucPubZwVYu', 'hong0987');

select * from identity;

insert into profile(email, nickname, identity_id)
values('hong@gmail.com', 'hong',  1);

select * from profile;

update identity
set profile_id = 1
where id = 1;

/*
id: 1
profile_id: 1
secret: $2a$12$pHuFp9mBfY5OrT36H19JF.nZM0icwZvYwmyyNhTaY7XucPubZwVYu	
username: hong0987
*/

-- profile 테이블
/*
id: 1
profile_id: 1
secret: $2a$12$pHuFp9mBfY5OrT36H19JF.nZM0icwZvYwmyyNhTaY7XucPubZwVYu	
username: hong0987
*/