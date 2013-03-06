<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/jsfunc.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="initAnswers()">
	<s:form action="answer_modify" method="post">
    <input type="hidden" id="answers" value="<s:property value="answers"/>"/>
    <input type="hidden" name="grade" value="<s:property value="grade"/>"/>
    <input type="hidden" name="qb.sid" value="${session.user.sid}"/>
 	<table width="80%"  align="center">
  <tr>
    <td>1.看待问题的态度</td>
  </tr>
  <tr>
    <td><label>
      <input type="radio" name="qb.question1" value="1" />
      </label>
    总是能够从积极角度，乐观地看待问题</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question1" value="2" />
    较多时候能从积极角度，乐观地看待问题</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question1" value="3" />
    不能从积极角度，乐观地看待问题</td>
  </tr>
  <tr>
    <td>2.对情绪管理的状况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question2" value="1" />
    面对挫折和不顺心的事，都能够很好地调节情绪</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question2" value="2" />
    面对挫折和不顺心的事，较多时能够调节好情绪</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question2" value="3" />
    遇到不顺心的事不能很好地调节情绪；面对挫折悲观失望、一蹶不振</td>
  </tr>
  <tr>
    <td>3.生活习惯与成瘾行为情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question3" value="1" />
    有以时间管理为核心的良好学习生活习惯，无影响正常学习生活的成瘾行为</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question3" value="2" />
    有合理的生活习惯，无影响正常学习生活的成瘾行为</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question3" value="3" />
    学习生活习惯不好，有影响正常学习生活的成瘾行为</td>
  </tr>
  <tr>
    <td>4.遵守社会道德规范情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question4" value="1" />
    无论与人相处，还是个人独处，都能遵守社会道德规范</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question4" value="2" />
    与人相处时，能够遵守社会道德规范</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question4" value="3" />
    时有不遵守社会道德规范的行为</td>
  </tr>
  <tr>
    <td>5.个人诚信与遵纪守法情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question5" value="1" />
    诚实守信，从来没有违纪违法行为</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question5" value="3" />
    有不诚信或违纪违法行为</td>
  </tr>
  <tr>
    <td>6.对义务与职责的态度</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question6" value="1" />
    自觉履行（对国家、集体、家庭等）应尽的义务，认真做好职责内工作，且有主动性、创造性</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question6" value="2" />
    能够履行应尽义务，对职责内工作能够认真负责</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question6" value="3" />
    不履行应尽义务，对职责内工作不能认真负责</td>
  </tr>
  <tr>
    <td>7.对国家、集体和他人的态度</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question7" value="1" />
    主动关注国家大事,关心集体，关爱他人，懂得感恩且付诸行动</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question7" value="2" />
    如有要求能够做有益于国家、集体和帮助他人的事，懂得感恩</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question7" value="3" />
    对国家和集体的事不热心，对他人漠不关心，不懂感恩</td>
  </tr>
  <tr>
    <td>8.参加服务社会志愿活动情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question8" value="1" />
    经常参加服务社会志愿行动，并有积极表现</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question8" value="2" />
    在有组织的情况下，能够参加服务社会志愿行动</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question8" value="3" />
    从不参加服务社会志愿行动，或虽参加却表现消极</td>
  </tr>
  <tr>
    <td>9.面对不同社会环境的表现</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question9" value="1" />
    以积极态度，主动较快地适应所面对的各种环境，尤其是不如意环境</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question9" value="2" />
    经过一段时间，通过努力后，能够适应所面对的多数环境</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question9" value="3" />
    难以适应新环境，尤其是不如意环境</td>
  </tr>
  <tr>
    <td>10.与熟悉的人关系状况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question10" value="1" />
    关系密切的朋友较多，与周围的人都能保持良好关系，且能团结与自己有分歧的人</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question10" value="2" />
    与周围的人能够良好相处</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question10" value="3" />
    与周围的人关系淡漠或经常关系紧张</td>
  </tr>
  <tr>
    <td>11.面对需要交往的不熟悉的人的表现情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question11" value="1" />
    能够创造条件与需要交往的人主动交往，并在较短时间内拉近人际距离</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question11" value="2" />
    能够去交往，但需要一段时间才能拉近人际关系</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question11" value="3" />
    拒绝主动交往或虽交往也难以拉近人际距离</td>
  </tr>
  <tr>
    <td>12.朋友缘情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question12" value="1" />
    班级内有较多同学愿意与你成为朋友</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question12" value="2" />
    班级内有一些人愿意与你成为朋友</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.rquestion12" value="3" />
    只有很少的人愿意与你成为朋友</td>
  </tr>
  <tr>
    <td>13.口头表达情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question13" value="1" />
    在众人面前，能够镇定自若并思路清晰、语言流畅地用普通话讲话，且有感染力  </td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question13" value="2" />
    在众人面前，能够用普通话明白通畅地讲话</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question13" value="3" />
    在众人面前，不敢讲话或讲述不清</td>
  </tr>
  <tr>
    <td>14.书面表达情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question14" value="1" />
    书面表达主题明确、层次分明、语法正确、修辞恰当、文体规范</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question14" value="2" />
    能用恰当的文体通顺地表达语意</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question14" value="3" />
    不能掌握各种文体的写作要求，或主题不明确，层次不清，行文中有较多错误</td>
  </tr>
  <tr>
    <td>15.不同社会角色与社会场合的表现情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question15" value="1" />
    作为不同社会角色，在不同社会场合，都能做到举止着装得体，言谈不俗，给人留下很好的印象</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question15" value="2" />
    作为不同社会角色，在不同社会场合，能够做到言谈、举止、着装得体</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question15" value="3" />
    举止言谈着装不得体，给人留下不好的印象</td>
  </tr>
  <tr>
    <td>16.把自己的想法变为行动的情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question16" value="1" />
    能够把自己的好想法，制定成切实可行的实施方案，并成功地付诸实践</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question16" value="2" />
    能够把部分想法变成行动</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question16" value="3" />
    从不把想法付诸实施，或实施方法不当，想法难以付诸实践</td>
  </tr>
  <tr>
    <td>17.执行力表现情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question17" value="1" />
    总是能够按照组织交办者的意图，结合实际情况，创造条件圆满完成交办事务</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question17" value="2" />
    多数时能够按照交办者的意图，结合实际情况，完成交办事务</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question17" value="3" />
    难以完成组织交办的事务</td>
  </tr>
  <tr>
    <td>18.参加社会实践情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question18" value="1" />
    积极参加社会实践活动，获得优秀等级</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question18" value="2" />
    参加社会实践活动，获得合格以上等级</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question18" value="3" />
    不主动参加社会实践活动，或虽参加但表现不佳</td>
  </tr>
  <tr>
    <td>19.对待新事物、新知识的态度</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question19" value="1" />
    主动学习新知识、接受新生事物</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question19" value="2" />
    需要时能够学习新知识、接受新生事物</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question19" value="3" />
    不愿学习新知识、接受新生事物</td>
  </tr>
  <tr>
    <td>20.参加创新类活动情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question20" value="1" />
    能积极参加创新类活动，创新成果突出</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question20" value="2" />
    能参加一些创新类活动，有一定的创新成果</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question20" value="3" />
    不参加创新类活动，或虽参加但没有创新成果</td>
  </tr>
  <tr>
    <td>21.与人沟通的表现情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question21" value="1" />
    遇事能够主动与人沟通，达到相互理解，且能让人接受自己的合理主张</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question21" value="2" />
    遇事能够主动与人沟通，达到相互理解</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question21" value="3" />
    不能主动沟通，或在沟通中双方分歧加深</td>
  </tr>
  <tr>
    <td>22.与他人合作共事情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question22" value="1" />
    能够积极承当好自己在团队中的角色，且能与团队成员主动合作，共同实现团队目标</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question22" value="2" />
    能够与团队成员合作共事，完成团队工作</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question22" value="3" />
    不能与团队成员合作共事</td>
  </tr>
  <tr>
    <td>23.为主组织团体活动情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question23" value="1" />
    多次为主成功组织复杂（多环节、多层次）、大型（100人以上）团体活动</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question23" value="2" />
    经常为主成功组织一般团体活动，或多次为主良好完成复杂大型团体活动中的部分组织协调任务</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question23" value="3" />
    组织团体活动不成功或从未组织过团体活动</td>
  </tr>
  <tr>
    <td>24.“生涯、职涯、学涯”规划制定与实施情况</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question24" value="1" />
    较好制定符合国家、社会发展需要和自身实际情况的“三涯”规划，并根据情况进行调整，且认真付诸实施，自觉发展成长</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question24" value="2" />
    制定了“三涯”规划，并大部分实施</td>
  </tr>
  <tr>
    <td><input type="radio" name="qb.question24" value="3" />
    无“三涯”规划或虽有规划但没有付诸实施</td>
  </tr>
  <tr>
  <td><s:submit value="修改"/></td>
  </tr>
</table>
</s:form>
</body>
</html>