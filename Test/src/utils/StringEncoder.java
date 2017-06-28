package utils;

import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

public class StringEncoder
{

	/**
	 * 指定文字列をMD5で暗号化処理する </br>
	 * １．特定文字列をMD5によってハッシュ化する。 </br>
	 * ２．顧客情報とハッシュ化後の特定文字列を結合する。</br>
	 * ３．2をMD5によってハッシュ化する。</br>
	 *
	 * @param str
	 *            MD5で暗号化したい文字列
	 * @param md5WordStr
	 *            特定文字列
	 * @return MD5で暗号化された文字列
	 * @throws Exception
	 *             暗号化異常
	 */
	public static String getMD5Str(String str, String md5WordStr) throws Exception
	{
		String returnStr = StringEncoder.getMD5Str(md5WordStr);
		returnStr = StringEncoder.getMD5Str(str.trim() + returnStr);
		return returnStr;
	}

	/**
	 * 指定文字列をMD5で暗号化処理する
	 *
	 * @param str
	 *            MD5で暗号化したい文字列
	 * @return MD5で暗号化された文字列
	 * @throws Exception
	 *             暗号化異常
	 */
	public static String getMD5Str(String str) throws Exception
	{
		String md5str = "";
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(str.getBytes("utf-8"));
		StringBuffer strbuf = new StringBuffer();
		int charVal = 0;
		for (int i = 0; i < bytes.length; i++)
		{
			charVal = bytes[i];
			if (charVal < 0)
			{
				charVal = charVal + 256;
			}
			if (charVal < 16)
			{
				strbuf.append("0");
			}
			strbuf.append(Integer.toHexString(charVal));
		}
		md5str = strbuf.toString();
		return md5str;
	}

	/**
	 * 指定文字列をMD5_BASE64で暗号化処理する </br>
	 * １．特定文字列をMD5によってハッシュ化する。 </br>
	 * ２．顧客情報とハッシュ化後の特定文字列を結合する。</br>
	 * ３．2をMD5によってハッシュ化する。</br>
	 * ４. 3をBASE64でエンコードして、最後の2桁"="を除去し、22桁とする。
	 *
	 * @param str
	 *            MD5で暗号化したい文字列
	 * @param md5WordStr
	 *            特定文字列
	 * @return MD5_BASE64で暗号化された文字列
	 * @throws Exception
	 *             暗号化異常
	 */
	public static String getMD5Base64Str(String str, String md5WordStr) throws Exception
	{
		String md5Base64Str = getMD5Base64Str(str, md5WordStr, true);
		return md5Base64Str;
	}

	/**
	 * 指定文字列をMD5_BASE64で暗号化処理する </br>
	 * １．特定文字列をMD5によってハッシュ化する。 </br>
	 * ２．顧客情報とハッシュ化後の特定文字列を結合する。</br>
	 * ３．2をMD5によってハッシュ化する。</br>
	 * ４. 3をBASE64でエンコードして、フラグはTrueの場合、最後2桁"="を除去し、22桁とする。
	 *
	 * @param str
	 *            MD5で暗号化したい文字列
	 * @param md5WordStr
	 *            特定文字列
	 * @return MD5_BASE64で暗号化された文字列
	 * @throws Exception
	 *             暗号化異常
	 */
	public static String getMD5Base64Str(String str, String md5WordStr, boolean flg) throws Exception
	{
		String md5Base64Str = "";
		String returnStr = StringEncoder.getMD5Str(md5WordStr);
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest((str.trim() + returnStr).getBytes("utf-8"));
		md5Base64Str = new BASE64Encoder().encode(bytes);

		// 最後の2桁"="を除去
		if (flg && md5Base64Str.lastIndexOf("==") == 22)
		{
			md5Base64Str = md5Base64Str.substring(0, md5Base64Str.length() - 2);
		}

		return md5Base64Str;
	}

}

