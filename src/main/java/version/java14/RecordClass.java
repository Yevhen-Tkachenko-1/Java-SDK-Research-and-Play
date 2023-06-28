package version.java14;

public record RecordClass(int id, String name) {

    public RecordClass{
        if(id < 0){
            throw new IllegalArgumentException("id = " + id);
        }
    }

    public static void main(String[] args) {

        //Record record2 = new Record(); // Expected 2 arguments but found 0
        RecordClass recordClass1 = new RecordClass(1, "Yevhen");
        RecordClass recordClass2 = new RecordClass(2, "Empty");
        RecordClass recordClass3 = new RecordClass(1, "Yevhen");

        System.out.println(recordClass1);
        System.out.println(recordClass1.id());
        System.out.println(recordClass1.name());
        System.out.println(recordClass1.equals(recordClass2));
        System.out.println(recordClass1.equals(recordClass3));

        RecordClass invalidRecordClass = new RecordClass(-1, ""); // java.lang.IllegalArgumentException: id = -1
    }
}
