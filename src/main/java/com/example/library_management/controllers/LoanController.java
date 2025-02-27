    package com.example.library_management.controllers;

    import com.example.library_management.dto.requests.LoanRequest;
    import com.example.library_management.dto.responses.LoanResponse;
    import com.example.library_management.services.LoanService;
    import jakarta.validation.Valid;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/loans")
    public class LoanController {

        private final LoanService loanService;

        public LoanController(LoanService loanService){
            this.loanService = loanService;
        }

        @PostMapping("/borrow")
        public ResponseEntity<LoanResponse> borrowBook(@Valid @RequestBody LoanRequest loanRequest) {
            LoanResponse loan = loanService.borrowBook(loanRequest.getUserId(), loanRequest.getBookId());
            return ResponseEntity.ok(loan);
        }

        @PostMapping("/return")
        public ResponseEntity<LoanResponse> returnBook(@Valid @RequestBody LoanRequest loanRequest) {
            LoanResponse loan = loanService.returnBook(loanRequest.getUserId(), loanRequest.getBookId());
            return ResponseEntity.ok(loan);
        }

        @GetMapping
        public ResponseEntity<List<LoanResponse>> getAllLoanedBooks() {
            List<LoanResponse> loanedBooks = loanService.borrowedBooksList();
            return ResponseEntity.ok(loanedBooks);
        }

    }
