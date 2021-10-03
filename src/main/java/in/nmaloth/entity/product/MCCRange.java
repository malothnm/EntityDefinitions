package in.nmaloth.entity.product;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MCCRange implements Comparable<MCCRange> {
    private int mccStart;
    private int mccEnd;

    @Override
    public boolean equals(Object o) {

        int value = ((MCCRange) o).getMccStart();
        if(mccStart <= value && mccEnd > value){
            return true;
        }
        if(mccEnd == 0) {
            if(mccStart == value){
                return true;
            }
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getMccStart());
    }

    @Override
    public int compareTo(MCCRange o) {

        if(this.mccStart <= o.getMccStart() && this.mccEnd > o.getMccStart()) {
            return 0;
        } else if(this.mccEnd < o.getMccStart()){
            return  -1;
        } else if(this.mccStart > o.getMccEnd()){
            return 1;
        }
        return 0;
    }
}
