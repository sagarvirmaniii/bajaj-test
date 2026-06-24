package com.bhfl.service;

import com.bhfl.dto.BfhlRequest;
import com.bhfl.dto.BfhlResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BfhlServiceImplTest {

    private final BfhlService service = new BfhlServiceImpl();

    @Test
    void processesCaseOne() {
        BfhlResponse response = service.process(new BfhlRequest(List.of("a", "1", "334", "4", "R", "$")));

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getUserId()).isEqualTo("sagar_virmani_24062026");
        assertThat(response.getEmail()).isEqualTo("sagar1384.be23@chitkara.edu.in");
        assertThat(response.getRollNumber()).isEqualTo("2310991384");
        assertThat(response.getOddNumbers()).containsExactly("1");
        assertThat(response.getEvenNumbers()).containsExactly("334", "4");
        assertThat(response.getAlphabets()).containsExactly("A", "R");
        assertThat(response.getSpecialCharacters()).containsExactly("$");
        assertThat(response.getSum()).isEqualTo("339");
        assertThat(response.getConcatString()).isEqualTo("Ra");
    }

    @Test
    void processesCaseTwo() {
        BfhlResponse response = service.process(new BfhlRequest(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b")));

        assertThat(response.getOddNumbers()).containsExactly("5");
        assertThat(response.getEvenNumbers()).containsExactly("2", "4", "92");
        assertThat(response.getAlphabets()).containsExactly("A", "Y", "B");
        assertThat(response.getSpecialCharacters()).containsExactly("&", "-", "*");
        assertThat(response.getSum()).isEqualTo("103");
        assertThat(response.getConcatString()).isEqualTo("ByA");
    }

    @Test
    void processesCaseThreeOnlyAlphabets() {
        BfhlResponse response = service.process(new BfhlRequest(List.of("A", "ABCD", "DOE")));

        assertThat(response.getOddNumbers()).isEmpty();
        assertThat(response.getEvenNumbers()).isEmpty();
        assertThat(response.getAlphabets()).containsExactly("A", "ABCD", "DOE");
        assertThat(response.getSpecialCharacters()).isEmpty();
        assertThat(response.getSum()).isEqualTo("0");
        assertThat(response.getConcatString()).isEqualTo("EoDdCbAa");
    }

    @Test
    void processesCaseFourEmptyData() {
        BfhlResponse response = service.process(new BfhlRequest(List.of()));

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getOddNumbers()).isEmpty();
        assertThat(response.getEvenNumbers()).isEmpty();
        assertThat(response.getAlphabets()).isEmpty();
        assertThat(response.getSpecialCharacters()).isEmpty();
        assertThat(response.getSum()).isEqualTo("0");
        assertThat(response.getConcatString()).isEmpty();
    }

    @Test
    void rejectsCaseFiveNullRequest() {
        assertThatThrownBy(() -> service.process(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("request body must not be null");
    }

    @Test
    void rejectsNullData() {
        assertThatThrownBy(() -> service.process(new BfhlRequest(null)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("data must not be null");
    }

    @Test
    void classifiesBlankMixedAndLargeValues() {
        BfhlResponse response = service.process(new BfhlRequest(List.of(
                "", "  ", "12a", "999999999999999999999999999999", "0"
        )));

        assertThat(response.getSpecialCharacters()).containsExactly("", "  ", "12a");
        assertThat(response.getOddNumbers()).containsExactly("999999999999999999999999999999");
        assertThat(response.getEvenNumbers()).containsExactly("0");
        assertThat(response.getSum()).isEqualTo("999999999999999999999999999999");
    }
}
