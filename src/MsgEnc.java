public class MsgEnc {
    Block [] blocks;
    Key key;
    SubTable table;
    final int blockSize=16;

    public MsgEnc()
    {
        key=new Key(10);
        table=new SubTable(blockSize);
    }
    public void setMessage(String str)
    {
        while (str.length()%16!=0)
        {
            str+=" ";
        }

        blocks = new Block[(int) Math.ceil((double)str.length()/blockSize)];

        for(int i=0; i<blocks.length; i++)
        {
            blocks[i]=new Block(str.substring(i*blockSize, (i+1)*blockSize));
        }
    }
    public void setBytes(byte [] bytes)
    {
        setMessage(new String(bytes));
    }
    public String getEncryptedString()
    {
        String ret="";
        for(Block block : blocks)
        {
            AES aes = new AES(block);
            ret+=aes.getEncrypted();
        }

        return ret;
    }
    public byte [] getEncryptedBytes()
    {
        return getEncryptedString().getBytes();
    }
    public String getDecryptedString()
    {
        String ret="";
        for(Block block : blocks)
        {
            AES aes = new AES(block);
            ret+=aes.getDecrypted();
        }

        return ret;
    }
    public byte [] getDecryptedBytes()
    {
        return getDecryptedString().getBytes();
    }
    public void printBlock()
    {
        for(Block block : blocks)
            block.print();
    }
}