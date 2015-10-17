INSERT INTO weizhu_community (community_id, community_name, board_id_order_str) VALUES (1, 'TestBBS', '3,2,1');

INSERT INTO weizhu_community_board (board_id, board_name, board_icon, board_desc, parent_board_id, is_leaf_board, is_hot) VALUES
(1, '新鲜事', '', '讲些八卦吧', NULL, 1, 1),
(2, '父板块', '', '测试父板块', NULL, 0, 0),
(3, '叶子板块', '', '测试叶子板块', 2, 1, 0);

INSERT INTO weizhu_community_post (post_id, post_title, board_id, create_user_id, create_time, is_hot, state, comment_id_max, is_sticky, sticky_time, is_recommend, recommend_time) VALUES
(100, '测试0', 1, 10000124196, 1432626471, 0, 'NORMAL', 10, 1, 1432626471, null, null),
(101, '测试1', 1, 10000124196, 1432636471, 0, 'NORMAL', 0, 1, 1432626472, null, null),
(102, '测试2', 1, 10000124196, 1432646471, 1, 'NORMAL', 0, 1, 1432626473, null, null),
(103, '测试3', 1, 10000124196, 1432656471, 0, 'NORMAL', 0, 1, 1432626474, null, null),
(104, '测试4', 1, 10000124196, 1432666471, 1, 'NORMAL', 0, 1, 1432626475, null, null),
(105, '测试5', 1, 10000124196, 1432676471, 0, 'NORMAL', 0, 1, 1432626476, null, null),
(106, '测试6', 1, 10000124196, 1432686471, 0, 'NORMAL', 0, 1, 1432626477, null, null),
(107, '测试7', 1, 10000124196, 1432696471, 0, 'NORMAL', 0, 1, 1432626478, null, null),
(108, '测试8', 1, 10000124196, 1432706471, 0, 'NORMAL', 0, null, null, null, null),
(109, '测试9', 1, 10000124196, 1432716471, 0, 'NORMAL', 0, null, null, null, null),
(110, '测试10', 1, 10000124196, 1432726471, 0, 'NORMAL', 0, null, null, null, null),

(111, '测试11', 1, 10000124196, 1432736471, 0, 'NORMAL', 0, 1, 1432626479, null, null),
(112, '测试12', 1, 10000124196, 1432746471, 0, 'NORMAL', 0, 1, 1432626480, null, null),
(113, '测试13', 1, 10000124196, 1432756471, 0, 'NORMAL', 0, null, null, 1, 1432626471),
(114, '测试14', 1, 10000124196, 1432766471, 0, 'NORMAL', 0, null, null, 1, 1432626471);

INSERT INTO weizhu_community_post_part (part_id, post_id, `text`, image_name) VALUES
(100001, 100, '哈哈哈👌', NULL);

INSERT INTO weizhu_community_post_comment (post_id, comment_id, reply_comment_id, content, create_user_id, create_time, state) VALUES 
(100, 1, NULL, '我是1楼', 10000124196, 1433626471, 'NORMAL'), 
(100, 2, NULL, '我是2楼', 10000124196, 1433626571, 'NORMAL'), 
(100, 3, NULL, '我是3楼', 10000124196, 1433626671, 'NORMAL'), 
(100, 4, NULL, '我是4楼', 10000124196, 1433626771, 'NORMAL'), 
(100, 5, NULL, '我是5楼', 10000124196, 1433626871, 'NORMAL'), 
(100, 6, NULL, '我是6楼', 10000124196, 1433626971, 'NORMAL'), 
(100, 7, NULL, '我是7楼', 10000124196, 1433627071, 'NORMAL'), 
(100, 8, NULL, '我是8楼', 10000124196, 1433627171, 'NORMAL'), 
(100, 9, NULL, '我是9楼', 10000124196, 1433627271, 'NORMAL'), 
(100, 10, NULL, '我是10楼', 10000124196, 1433627371, 'NORMAL');

INSERT INTO weizhu_community_post_like (post_id, user_id, create_time) VALUES
(100, 10000124196, 1433626471),
(110, 10000124196, 1432726471);
