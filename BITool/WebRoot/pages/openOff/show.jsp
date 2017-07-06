<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<table border="1" cellpadding="5" cellspacing="0" width="30%">
		<tr>
			<td align="center">开闭局管理</td>
		</tr>
		<tr>
			<td>
				<table border="1" cellpadding="5" cellspacing="0" width="100%">
					<tr>
						<td colspan="4" align="center">自动开闭局管理</td>
					</tr>
					<tr>
						<td>开始时刻</td>
						<td><select>
								<option>00</option>
								<option>01</option>
								<option>02</option>
								<option>03</option>
								<option>04</option>
								<option>05</option>
								<option>06</option>
								<option>07</option>
								<option>08</option>
								<option>09</option>
								<option>10</option>
								<option>11</option>
								<option>12</option>
								<option>13</option>
								<option>14</option>
								<option>15</option>
								<option>16</option>
								<option>17</option>
								<option>18</option>
								<option>19</option>
								<option>20</option>
								<option>21</option>
								<option>22</option>
								<option>23</option>
						</select></td>
						<td colspan="2"><select>
								<option>00</option>
								<option>10</option>
								<option>20</option>
								<option>30</option>
								<option>40</option>
								<option>50</option>
						</select></td>
					</tr>
					<tr>
						<td>终了时刻</td>
						<td><select>
								<option>00</option>
								<option>01</option>
								<option>02</option>
								<option>03</option>
								<option>04</option>
								<option>05</option>
								<option>06</option>
								<option>07</option>
								<option>08</option>
								<option>09</option>
								<option>10</option>
								<option>11</option>
								<option>12</option>
								<option>13</option>
								<option>14</option>
								<option>15</option>
								<option>16</option>
								<option>17</option>
								<option>18</option>
								<option>19</option>
								<option>20</option>
								<option>21</option>
								<option>22</option>
								<option>23</option>
						</select></td>
						<td><select>
								<option>00</option>
								<option>10</option>
								<option>20</option>
								<option>30</option>
								<option>40</option>
								<option>50</option>
						</select></td>
						<td><button>变更</button></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="1" cellpadding="5" cellspacing="0" width="100%">
					<tr>
						<td colspan="3"  align="center">手动状态变更</td>
					</tr>
					<tr>
						<td>现在状态</td>
						<td>变更后状态</td>
						<td></td>
					</tr>
					<tr>
						<td>手动开闭局管理</td>
						<td><select>
								<option>自动开闭局管理</option>
								<option>手动开闭局管理</option>
								<option>Maintanance开具</option>
						</select></td>
						<td><button>变更</button></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
