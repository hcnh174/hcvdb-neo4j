package edu.hiro.hcv.morphia;

public class Virus extends Pathogen
{
	protected Baltimore baltimore;
	protected NucleicAcidType nucleicAcidType;
	protected Shape shape;
	protected Strandedness strandedness;
	protected Sense sense;
	protected Boolean reverseTranscription;
	protected Boolean envelope;
	protected String size;
	
	public Virus() {}
	
	public Virus(String identifier)
	{
		super(identifier);
	}
	
	public enum NucleicAcidType
	{
		RNA("RNA","RNA"),
		DNA("DNA","DNA"),
		BOTH("Both DNA and RNA","Both");

		private String label;
		private String brief;
		
		NucleicAcidType(String label, String brief)
		{
			this.label=label;
			this.brief=brief;
		}
		
		public String getLabel() {return this.label;}
		public String getBrief() {return this.brief;}
	};
	
	public enum Shape
	{
		CIRCULAR("Circular","Circular"),
		LINEAR("Linear","Linear"),
		SEGMENTED("Segmented","Segmented");

		private String label;
		private String brief;
		
		Shape(String label, String brief)
		{
			this.label=label;
			this.brief=brief;
		}
		
		public String getLabel() {return this.label;}
		public String getBrief() {return this.brief;}
	};
	
	public enum Strandedness
	{
		SINGLE("Single-stranded","single"),
		DOUBLE("Double-stranded","double"),
		BOTH("Double-stranded with regions of single-strandedness","both");

		private String label;
		private String brief;
		
		Strandedness(String label, String brief)
		{
			this.label=label;
			this.brief=brief;
		}
		
		public String getLabel() {return this.label;}
		public String getBrief() {return this.brief;}
	};
	
	public enum Sense
	{
		POSITIVE("Positive sense (+)","+"),
		NEGATIVE("Negative sense (-)","-"),
		AMBISENSE("Ambisense","+/-");
		
		private String label;
		private String brief;
		
		Sense(String label, String brief)
		{
			this.label=label;
			this.brief=brief;
		}
		
		public String getLabel() {return this.label;}
		public String getBrief() {return this.brief;}
	};
	
	public enum Baltimore
	{
		I("I. Double-stranded DNA", "dsDNA"),
		II("II. Single-stranded (+) sense DNA", "ssDNA"),
		III("III. Double-stranded RNA", "dsRNA"),
		IV("IV. Single-stranded (+) sense RNA", "(+)ssRNA"),
		V("V. Single-stranded (-) sense RNA", "(-)ssRNA"),
		VI("VI. Single-stranded (+) sense RNA with DNA intermediate in life-cycle", "ssRNA-RT"),
		VII("VII. Double-stranded DNA with RNA intermediate", "dsDNA-RT");
		
		private String label;
		private String brief;
		
		Baltimore(String label, String brief)
		{
			this.label=label;
			this.brief=brief;
		}
		
		public String getLabel() {return this.label;}
		public String getBrief() {return this.brief;}
	}
}