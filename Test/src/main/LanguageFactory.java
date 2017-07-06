package main;

public class LanguageFactory
{
	public static ILanguage getLanguageInstance()
	{
		ILanguage languageInstance = null;
		String languageType = Hello.class.getAnnotation(LanguageInfo.class).languageType();
		try
		{
			languageInstance = (ILanguage) Class.forName(languageType).newInstance();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return languageInstance;
	}
}
