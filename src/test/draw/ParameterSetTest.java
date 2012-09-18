package test.draw;

import junit.framework.Assert;

import org.junit.Test;

import draw.ParameterSet;

public class ParameterSetTest {
	
	@Test
	public void testAssign() {
		ParameterSet params = new ParameterSet();
		double dIn = 10.5;
		boolean bIn = true;
		params.set("aDouble", dIn);
		params.set("aBoolean", bIn);
		double dOut = params.getDouble("aDouble");
		boolean bOut = params.getBoolean("aBoolean");
		Assert.assertEquals(bIn, bOut);
		Assert.assertEquals(dIn, dOut);
	}

}
