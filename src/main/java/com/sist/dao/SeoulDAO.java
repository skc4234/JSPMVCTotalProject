package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;
public class SeoulDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 기능
	// 1. 목록 출력
	/*
<select id="seoulListData" resultType="SeoulVO" parameterType="hashMap">
	SELECT no,title,poster
	FROM ${table}
	ORDER BY no ASC
	OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
</select>
	 */
	public static List<SeoulVO> seoulListData(Map map) {
		SqlSession session=ssf.openSession();
		List<SeoulVO> list=session.selectList("seoulListData",map);
		if(session!=null) session.close();
		return list;
	}
	
	// 1-1. 총페이지
	/*
<select id="seoulTotalPage" resultType="int" parameterType="hashMap">
	SELECT CEIL(COUNT(*)/12.0) FROM ${table}
</select>
	 */
	public static int seoulTotalPage(Map map) {
		SqlSession session=ssf.openSession();
		int total=session.selectOne("seoulTotalPage",map);
		if(session!=null) session.close();
		return total;
	}
	
	// 2. 상세보기
	/*
<update id="hitIncrement" parameterType="hashMap">
	UPDATE ${table} SET hit=hit+1 WHERE no=#{no}
</update>
<select id="seoulDetailData" resultType="SeoulVO" parameterType="hashMap">
	SELECT *
	FROM ${table}
	WHERE no=#{no}
</select>
	 */
	// 데이터 읽기 : select
	// 데이터 갱신 : insert,update,delete ==> commit 필요
	// SqlSession session=ssf.openSession() : Auto commit(false)
	// ssf.openSession(true) ==> Auto commit
	// 트랜잭션 : 일괄처리 => 전부 수행하거나 실패 시 전부 rollback
	public static SeoulVO seoulDetailData(Map map) {
		SqlSession session=ssf.openSession(true);
		
		/*
		 * if(!map.get("table").equals("seoul_hotel")) {
		 * 
		 * session.update("hitIncrement",map); }
		 */
		
		String t=(String)map.get("table");
		if(!t.endsWith("hotel")) {
			session.update("hitIncrement",map);
		}
		//session.update("hitIncrement",map);
		//session.commit();
		SeoulVO vo=session.selectOne("seoulDetailData",map);
		if(session!=null) session.close();
		return vo;
		
	}
}
