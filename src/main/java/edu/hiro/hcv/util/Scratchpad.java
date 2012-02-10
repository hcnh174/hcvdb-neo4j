package edu.hiro.hcv.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import edu.hiro.util.CException;
import edu.hiro.util.FileHelper;


public class Scratchpad
{
	
	// mvn test -Pscratchpad -e
	public static void main(String ... args)
	{
		try
		{
			String infile="test.svg";
			String outfile="test.png";
			String svg=FileHelper.readFile(infile);
			byte[] image=renderSvg(svg);
			OutputStream out=new FileOutputStream(outfile);
			writeImage(image,out);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public static byte[] renderSvg(String svg)
	{
		try
		{
			Reader reader=new StringReader(svg);
			ByteArrayOutputStream stream=new ByteArrayOutputStream();
			PNGTranscoder transcoder=new PNGTranscoder();
			//KEY_FORCE_TRANSPARENT_WHITE;
			//transcoder.addTranscodingHint(PNGTranscoder.KEY_FORCE_TRANSPARENT_WHITE,true);
	        TranscoderInput input = new TranscoderInput(reader);
	        TranscoderOutput output=new TranscoderOutput(stream);
	        transcoder.transcode(input,output);
	        return stream.toByteArray();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeImage(byte[] image, OutputStream stream)
	{
		try
		{
			stream.write(image);
			stream.flush();
		}
		catch (IOException e)
		{
			throw new CException(e);
		}
	}
}
