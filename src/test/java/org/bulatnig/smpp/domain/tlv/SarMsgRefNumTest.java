package org.bulatnig.smpp.domain.tlv;

import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.assertEquals;

import org.bulatnig.smpp.util.SmppByteBuffer;
import org.junit.Test;
import org.bulatnig.smpp.pdu.tlv.ParameterTag;
import org.bulatnig.smpp.pdu.tlv.SarMsgRefNum;
import org.bulatnig.smpp.pdu.tlv.TLVException;
import org.bulatnig.smpp.pdu.tlv.TLVNotFoundException;
import org.bulatnig.smpp.util.WrongParameterException;

public class SarMsgRefNumTest {
	
	// Used for backward compatibility (IDEs, Ant and JUnit 3 text runner)
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SarMsgRefNumTest.class);
	}
	
	@Test
	public void testSMRNConstructor1() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x020c);
		bb.appendShort(0x0002);
		bb.appendShort(0x1111);
		SarMsgRefNum smrn = new SarMsgRefNum(bb.array());
		assertEquals(ParameterTag.SAR_MSG_REF_NUM, smrn.getTag());
		assertEquals(6, smrn.getBytes().length);
		assertEquals(4369, smrn.getValue());
		assertEquals("020c00021111", new SmppByteBuffer(smrn.getBytes()).getHexDump());
	}

    @Test(expected = TLVNotFoundException.class)
	public void testSMRNConstructor2() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0000);
		bb.appendShort(0x0002);
		bb.appendByte((byte)0x1111);
		new SarMsgRefNum(bb.array());
	}
	
	@Test(expected= TLVException.class)
	public void testSMRNConstructor3() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x020c);
		bb.appendShort(0x0001);
		bb.appendShort(0x1111);
		new SarMsgRefNum(bb.array());
	}
	
	@Test
	public void testSMRNConstructor4() throws TLVException, WrongParameterException {
		SarMsgRefNum smrn = new SarMsgRefNum((short)112);
		assertEquals(ParameterTag.SAR_MSG_REF_NUM, smrn.getTag());
		assertEquals(6, smrn.getBytes().length);
		assertEquals(112, smrn.getValue());
		assertEquals("020c00020070", new SmppByteBuffer(smrn.getBytes()).getHexDump());
	}
	
	@Test(expected= TLVException.class)
	public void testSMRNConstructor5() throws WrongParameterException, TLVException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x020c);
		bb.appendShort(0x0001);
		bb.appendByte((byte)0x11);
		new SarMsgRefNum(bb.array());
	}
	
	@Test(expected=ClassCastException.class)
	public void testSMRNConstructor6() throws TLVException, WrongParameterException {
		SmppByteBuffer bb = new SmppByteBuffer();
		bb.appendShort(0x0005);
		bb.appendShort(0x0002);
		bb.appendShort(0x7fff);
		new SarMsgRefNum(bb.array());
	}

}