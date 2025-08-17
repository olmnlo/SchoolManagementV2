package org.example.schoolmanagement.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.Api.ApiResponse;
import org.example.schoolmanagement.DTO.AddressDTO;
import org.example.schoolmanagement.Model.Address;
import org.example.schoolmanagement.Service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses(){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAllAddresses());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addAddress(@Valid @RequestBody AddressDTO addressDTO){
        addressService.assignAddressToTeacher(addressDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("address added successfully"));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable Integer addressId,@Valid @RequestBody AddressDTO addressDTO){
        addressService.updateAddress(addressId,addressDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("address updated successfully"));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("address deleted successfully"));
    }


}
