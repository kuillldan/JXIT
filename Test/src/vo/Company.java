package vo;

import java.util.Arrays;
import java.util.Date;

public class Company
{
	private Integer _Integer;
	private int _int;
	private Double _Double;
	private double _double;
	private String _String;
	private Date _Date;
	
	private Integer[] _Integers;
	private int[] _ints;
	private Double[] _Doubles;
	private double[] _doubles;
	private String[] _Strings;
	public Integer get_Integer()
	{
		return _Integer;
	}
	public void set_Integer(Integer _Integer)
	{
		this._Integer = _Integer;
	}
	public int get_int()
	{
		return _int;
	}
	public void set_int(int _int)
	{
		this._int = _int;
	}
	public Double get_Double()
	{
		return _Double;
	}
	public void set_Double(Double _Double)
	{
		this._Double = _Double;
	}
	public double get_double()
	{
		return _double;
	}
	public void set_double(double _double)
	{
		this._double = _double;
	}
	public String get_String()
	{
		return _String;
	}
	public void set_String(String _String)
	{
		this._String = _String;
	}
	public Date get_Date()
	{
		return _Date;
	}
	public void set_Date(Date _Date)
	{
		this._Date = _Date;
	}
	public Integer[] get_Integers()
	{
		return _Integers;
	}
	public void set_Integers(Integer[] _Integers)
	{
		this._Integers = _Integers;
	}
	public int[] get_ints()
	{
		return _ints;
	}
	public void set_ints(int[] _ints)
	{
		this._ints = _ints;
	}
	public Double[] get_Doubles()
	{
		return _Doubles;
	}
	public void set_Doubles(Double[] _Doubles)
	{
		this._Doubles = _Doubles;
	}
	public double[] get_doubles()
	{
		return _doubles;
	}
	public void set_doubles(double[] _doubles)
	{
		this._doubles = _doubles;
	}
	public String[] get_Strings()
	{
		return _Strings;
	}
	public void set_Strings(String[] _Strings)
	{
		this._Strings = _Strings;
	}
	@Override
	public String toString()
	{
		return "Company [_Integer=" + _Integer + ", _int1=" + _int + ", _Double=" + _Double + ", _double=" + _double
				+ ", _String=" + _String + ", _Date=" + _Date + ", _Integers=" + Arrays.toString(_Integers)
				+ ", _ints=" + Arrays.toString(_ints) + ", _Doubles=" + Arrays.toString(_Doubles) + ", _doubles="
				+ Arrays.toString(_doubles) + ", _Strings=" + Arrays.toString(_Strings) + "]";
	}
}
