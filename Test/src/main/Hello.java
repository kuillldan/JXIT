package main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@LanguageInfo(languageType="main.English")
public class Hello
{
	public static void main(String[] args) throws Exception
	{
		ILanguage languageInstance = LanguageFactory.getLanguageInstance();
		languageInstance.say();
	}
}
