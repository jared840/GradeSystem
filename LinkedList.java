public class LinkedList {
    private Link head;
    private Link last;
    private Link current;
    private int listLength;

    LinkedList() {
        this.head = new Link(null);
        current = head;
        last = head;
        listLength = 0;
    }


    public int getSize() {
        return this.listLength;
    }

    public boolean isEmpty() {
        if(this.listLength==0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void goTo(int pos) {
        current = head;
        int i = 0;
        while(i<pos) {
            current = current.next();
            i++;
        }
    }

    public void addToListEnd(String assess, double weigh) {
        Link temp = new Link(assess, weigh);

        if(this.isEmpty()==true) {
            head = temp;
            this.listLength++;
            return;
        }
        current = head;
        while(current.next() != null) {
            last = current;
            current = current.next();
        }

        current.setNext(temp);
        listLength++;
    }

    public void PrintList() {
        current = head;
        while(current!=null) {
            current.printPayload();
            current = current.next();
        }
    }


   
}




class Link {
    private String assessment;
    private double weight;
    private Link next;

    Link(String proposedAssessment, double proposedWeight, Link nextOne) {
        this.assessment = proposedAssessment;
        this.weight = proposedWeight;
        this.next = nextOne;
        
    }

    Link (Link nextOne) {
        this.next = nextOne;
    }

    Link() {
        //nothing
    }

    Link (String proposedAssessment, double toWeight) {
        this.assessment = proposedAssessment;
        this.weight = toWeight;
    }


public Link next() {
    return this.next;

}

    public String getAssessment() {
        return this.assessment;
    }

    public double getWeight() {
        return this.weight;
    }

    public void printPayload() {
        System.out.println(this.assessment + ": " + this.weight);
    }

    public Link setNext (Link nextOne) {
        this.next = nextOne;
        return this.next;
    }

    public void setData (String assess, double weigh) {
        this.assessment = assess;
        this.weight = weigh;
    }

}
