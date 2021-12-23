package ru.iteco.account.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "transaction", schema = "bank")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "source_bank_book_id", nullable = false)
    Integer sourceBankBookId;

    @Column(name = "target_bank_book_id", nullable = false)
    Integer targetBankBookId;

    @Column(name = "amount", nullable = false)
    BigDecimal amount;

    @Column(name = "initiation_date", nullable = false)
    LocalDateTime initiationDate;

    @Column(name = "completion_date", nullable = false)
    LocalDateTime completionDate;

    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    StatusEntity status;

    @OneToOne
    @JoinColumn(name = "source_bank_book_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BankBookEntity sourceBankBook;

    @OneToOne
    @JoinColumn(name = "target_bank_book_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BankBookEntity targetBankBook;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransactionEntity that = (TransactionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(sourceBankBookId, that.sourceBankBookId) && Objects.equals(targetBankBookId, that.targetBankBookId) && Objects.equals(amount, that.amount) && Objects.equals(initiationDate, that.initiationDate) && Objects.equals(completionDate, that.completionDate) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceBankBookId, targetBankBookId, amount, initiationDate, completionDate, status);
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", sourceBankBookId=" + sourceBankBookId +
                ", targetBankBookId=" + targetBankBookId +
                ", amount=" + amount +
                ", initiationDate=" + initiationDate +
                ", completionDate=" + completionDate +
                '}';
    }
}
