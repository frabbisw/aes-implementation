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
            str+=" ";

        blocks = new Block[(int) Math.ceil((double)str.length()/blockSize)];

        for(int i=0; i<blocks.length; i++)
        {
            blocks[i]=new Block(str.substring(i*blockSize, (i+1)*blockSize));
        }
    }
    public String getEncrypted()
    {
        String ret="";
        for(Block block : blocks)
        {
            AES aes = new AES(block);
            ret+=aes.getEncrypted();
        }

        return ret;
    }
    public String getDecrypted()
    {
        String ret="";
        for(Block block : blocks)
        {
            AES aes = new AES(block);
            ret+=aes.getDecrypted();
        }

        return ret;
    }
    public void printBlock()
    {
        for(Block block : blocks)
            block.print();
    }
}