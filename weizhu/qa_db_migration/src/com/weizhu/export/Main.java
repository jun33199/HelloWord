package com.weizhu.export;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.weizhu.migration.Util;
import com.weizhu.proto.QAProtos;
import com.weizhu.proto.QAProtos.Answer;
import com.weizhu.proto.QAProtos.AnswerLike;
import com.weizhu.proto.QAProtos.Question;

public class Main {
	public static void main(String [] arg) throws Exception{
		new Main().qaDataMigration();
	}
	
	public void qaDataMigration(){
		Connection conn=null;
		List<QAProtos.Question> questionList=null;
		try{
			conn=Util.getConnection();
			// 获取问题列表
			questionList=this.getQuestion(conn);
			List<Long> questionIdList=new ArrayList<Long>();
			for(QAProtos.Question question:questionList){
				questionIdList.add(question.getQuestionId());
			}
			//获取回答列表
			Map<Long,List<QAProtos.Answer>> quesAnswMap=this.getAnswer(conn, questionIdList);
			
			List<Long> answerIdList=new ArrayList<Long>();
			for(List<QAProtos.Answer> answerList:quesAnswMap.values()){
				for(QAProtos.Answer answer:answerList){
					answerIdList.add(answer.getAnswerId());
				}
			}
			//获取点赞列表
			Map<Long,List<QAProtos.AnswerLike>> answerLikeMap=this.getAnswerlike(conn, answerIdList);
			
			//插入数据
			try {
				this.createTables(conn);
				this.insertData(conn,questionList,quesAnswMap,answerLikeMap);
				System.out.println("问答模块数据迁移成功！");
			} catch (SQLException e) {
				System.out.println("问答模块数据迁移失败！");
				e.printStackTrace();
			}
			
			
			
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}	
		
	}
	
	private void createTables(Connection conn) throws SQLException {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.addBatch("DROP TABLE IF EXISTS weizhu_qa_question;");
			st.addBatch("DROP TABLE IF EXISTS weizhu_qa_answer;");
			st.addBatch("DROP TABLE IF EXISTS weizhu_qa_answer_like;");
			st.addBatch("DROP TABLE IF EXISTS weizhu_qa_question_category;");
			st.addBatch("CREATE TABLE IF NOT EXISTS weizhu_qa_question_category(`category_id` INT NOT NULL AUTO_INCREMENT,`category_name` VARCHAR(191) NOT NULL,`user_id` BIGINT,`admin_id` BIGINT,`create_time` INT NOT NULL,  PRIMARY KEY (`category_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ;");
			st.addBatch("CREATE TABLE IF NOT EXISTS weizhu_qa_question (`question_id` INT NOT NULL AUTO_INCREMENT,`question_content` TEXT, `user_id` BIGINT, `admin_id` BIGINT,`category_id` INT NOT NULL,  `create_time`  INT NOT NULL, PRIMARY KEY (`question_id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin; ");
			st.addBatch("CREATE TABLE IF NOT EXISTS weizhu_qa_answer ( `answer_id` INT NOT NULL AUTO_INCREMENT,`question_id` INT NOT NULL,`user_id`  BIGINT,  `admin_id`  BIGINT,`answer_content`  TEXT, `create_time` INT NOT NULL,  PRIMARY KEY (`answer_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
			st.addBatch("CREATE TABLE IF NOT EXISTS weizhu_qa_answer_like (`user_id` BIGINT NOT NULL, `answer_id` INT NOT NULL,PRIMARY KEY (`user_id`,`answer_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
			st.executeBatch();
		
		} finally {
			Util.closeQuietly(null,st);
		}
		
	}

	private void insertData(Connection conn,List<Question> questionList, Map<Long, List<Answer>> quesAnswMap, Map<Long, List<AnswerLike>> answerLikeMap) {

		try {
			List<Integer> questionIdListNew=insertQuestionData(conn,questionList);
			Map<Integer, List<Answer>> quesAnswMapNew=new HashMap<Integer, List<Answer>> ();
			if(questionList.size()!=questionIdListNew.size()){
				return;
			}else{
				for(int i=0;i<questionIdListNew.size();i++){
					Question question=questionList.get(i);
					quesAnswMapNew.put(questionIdListNew.get(i), quesAnswMap.get(question.getQuestionId()));
				}
			}
			
			List<Integer> answerIdListNew=insertAnswerData(conn,quesAnswMapNew);
			
			
			Map<Integer, List<AnswerLike>> answerLikeMapNew=new HashMap<Integer, List<AnswerLike>> ();
			List<Long> answerIdList=new ArrayList<Long>();
			for(List<QAProtos.Answer> answerList:quesAnswMap.values()){
				for(QAProtos.Answer answer:answerList){
					answerIdList.add(answer.getAnswerId());
				}
			}
			if(answerIdList.size()!=answerIdListNew.size()){
				return;
			}else{
				for(int i=0;i<answerIdList.size();i++){
					answerLikeMapNew.put(answerIdListNew.get(i), answerLikeMap.get(answerIdList.get(i)));
				}
			}
			insertLikeData(conn,answerLikeMapNew);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private List<Integer> insertQuestionData(Connection conn,List<Question> questionList) throws SQLException {
		if (questionList.isEmpty()) {
			return Collections.emptyList();
		}
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO weizhu_qa_question(question_content,user_id,admin_id,category_id,create_time) VALUES (?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
			for(Question question:questionList){
				pstmt.setString(1, question.getQuestionContent());
				pstmt.setLong(2, Long.parseLong(question.getUserId()));
				pstmt.setNull(3, java.sql.Types.BIGINT);
				pstmt.setInt(4, 1);
				pstmt.setInt(5, question.getCreateTime());
				pstmt.addBatch();
			}

			pstmt.executeBatch();
			rs=pstmt.getGeneratedKeys();
			List<Integer> idList=new ArrayList<Integer>();
			while(rs.next()){
				idList.add(rs.getInt(1));
			}
			return idList;
		} finally {
			Util.closeQuietly(rs,pstmt);
		}
	}
	
	private List<Integer> insertAnswerData(Connection conn,Map<Integer, List<Answer>> quesAnswMap) throws SQLException {
		if (quesAnswMap.isEmpty()) {
			return Collections.emptyList();
		}
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO weizhu_qa_answer(question_id,user_id,admin_id,answer_content,create_time) VALUES (?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
			for(Entry<Integer, List<Answer>> entry:quesAnswMap.entrySet()){
				if(entry.getValue()==null||entry.getValue().isEmpty()){
					continue;
				}
				for(Answer answer:entry.getValue()){
					pstmt.setInt(1, entry.getKey());
					pstmt.setLong(2, Long.parseLong(answer.getUserId()));
					pstmt.setNull(3, java.sql.Types.BIGINT);
					pstmt.setString(4, answer.getAnswerContent());
					pstmt.setInt(5, answer.getCreateTime());
					pstmt.addBatch();
				}
			}

			pstmt.executeBatch();
			rs=pstmt.getGeneratedKeys();
			List<Integer> idList=new ArrayList<Integer>();
			while(rs.next()){
				idList.add(rs.getInt(1));
			}
			return idList;

		} finally {
			Util.closeQuietly(rs,pstmt);
		}
	}
	
	private void insertLikeData(Connection conn,Map<Integer, List<AnswerLike>> answerLikeMap) throws SQLException {
		if (answerLikeMap.isEmpty()) {
			return;
		}
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT IGNORE INTO weizhu_qa_answer_like(user_id,answer_id) VALUES (?,?);");
			for(Entry<Integer, List<AnswerLike>> entry:answerLikeMap.entrySet()){
				if(entry.getValue()==null||entry.getValue().isEmpty()){
					continue;
				}
				for(AnswerLike answerLike:entry.getValue()){
					pstmt.setLong(1, Long.parseLong(answerLike.getUserId()));
					pstmt.setInt(2, entry.getKey());
					pstmt.addBatch();
				}
			}

			pstmt.executeBatch();

		} finally {
			Util.closeQuietly(null,pstmt);
		}
	}
	
	private List<QAProtos.Question> getQuestion(Connection conn){
		String sql="select questionId,title,userId,addtime from question;";
		Statement st=null;
		ResultSet rs=null;
		
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			List<QAProtos.Question> questionList=new ArrayList<QAProtos.Question>();
			QAProtos.Question.Builder questionBuilder =QAProtos.Question.newBuilder();
			while(rs.next()){
				questionBuilder.clear();
				questionBuilder.setQuestionId(rs.getLong("questionId"));
				questionBuilder.setQuestionContent(rs.getString("title"));
				questionBuilder.setUserId(rs.getString("userId"));
				questionBuilder.setCreateTime(Util.getDate(rs.getDate("addtime")));		
				questionBuilder.setCategoryId(1);
				questionList.add(questionBuilder.build());
			}
			return questionList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取问题出错",e);
		}finally{
			Util.closeQuietly(rs, st);
			
		}
		
	}
	
	private Map<Long,List<QAProtos.Answer>> getAnswer(Connection conn, Collection<Long> questionIds){
		
		String questionIdStr = Util.COMMA_JOINER.join(questionIds);
		StringBuilder sql = new StringBuilder();
		sql.append("select answerId,questionId,content,userId,addtime from answer where questionId in (").append(questionIdStr).append(");");
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			Map<Long,List<QAProtos.Answer>> quesAnswMap=new HashMap<Long,List<QAProtos.Answer>>();
			List<QAProtos.Answer> answerList=null;
			QAProtos.Answer.Builder answerBuilder =QAProtos.Answer.newBuilder();
			while(rs.next()){
				answerBuilder.clear();
				answerBuilder.setAnswerId(rs.getLong("answerId"));
				answerBuilder.setQuestionId(rs.getLong("questionId"));
				answerBuilder.setAnswerContent(rs.getString("content"));
				answerBuilder.setUserId(rs.getString("userId"));
				answerBuilder.setCreateTime(Util.getDate(rs.getDate("addtime")));		
				
				if(quesAnswMap.containsKey(answerBuilder.getQuestionId())){
					answerList=quesAnswMap.get(answerBuilder.getQuestionId());				
				}else{
					answerList=new ArrayList<QAProtos.Answer>();
				}
				answerList.add(answerBuilder.build());
				quesAnswMap.put(answerBuilder.getQuestionId(), answerList);
			}

			return quesAnswMap;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取问题出错",e);
		}finally{
			Util.closeQuietly(rs, st);		
		}		
	}
	private Map<Long,List<QAProtos.AnswerLike>> getAnswerlike(Connection conn, Collection<Long> answerIds){
		
		String anwerIdStr = Util.COMMA_JOINER.join(answerIds);
		StringBuilder sql = new StringBuilder();
		sql.append("select answerId,userId,recordId from supportrecord where answerId in (").append(anwerIdStr).append(");");
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			Map<Long,List<QAProtos.AnswerLike>> answLikeMap=new HashMap<Long,List<QAProtos.AnswerLike>>();
			List<QAProtos.AnswerLike> answerLikeList=null;
			QAProtos.AnswerLike.Builder answerlikeBuilder =QAProtos.AnswerLike.newBuilder();
			while(rs.next()){
				answerlikeBuilder.clear();
				answerlikeBuilder.setAnswerId(rs.getLong("answerId"));
				answerlikeBuilder.setUserId(rs.getString("userId"));
				answerlikeBuilder.setLikeId(rs.getLong("recordId"));
				
				if(answLikeMap.containsKey(answerlikeBuilder.getAnswerId())){
					answerLikeList=answLikeMap.get(answerlikeBuilder.getAnswerId());				
				}else{
					answerLikeList=new ArrayList<QAProtos.AnswerLike>();
				}
				answerLikeList.add(answerlikeBuilder.build());
				answLikeMap.put(answerlikeBuilder.getAnswerId(), answerLikeList);
			}

			return answLikeMap;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取问题出错",e);
		}finally{
			Util.closeQuietly(rs, st);		
		}		
	}
}
