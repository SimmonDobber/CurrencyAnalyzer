package com.example.currencyanalyzerbackend.currency;

import com.example.currencyanalyzerbackend.currency.dto.CurrencyRequestedDto;
import com.example.currencyanalyzerbackend.currency.dto.CurrencyResponseDto;
import com.example.currencyanalyzerbackend.date.RequestDataDto;
import lombok.AllArgsConstructor;

import static com.example.currencyanalyzerbackend.date.DateMapper.stringToDate;

@AllArgsConstructor
public class CurrencyService {

    private final CurrencyRequester currencyRequester;

    public CurrencyResponseDto getCurrencyRecords(RequestDataDto requestDataDto){
        CurrencyRequestedDto requestedDto = currencyRequester.getRequestedCurrency(requestDataDto);
        Currency currency = CurrencyMapper.requestedDtoToObject(requestedDto);
        currency.fillEmptyDays(requestDataDto);
        currency.trimRecordsToStartDate(stringToDate(requestDataDto.getStartDate()));
        currency.setRecordsDifferences();
        return CurrencyMapper.objectToResponseDto(currency);
    }

}
