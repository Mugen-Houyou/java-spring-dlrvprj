package com.gabe.dlrvprjgroup.gabedlrvdb.account;

import com.gabe.dlrvprjgroup.gabedlrvdb.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder // 부모가 가진 변수도 지정할 수 있게끔.
@Data
@EqualsAndHashCode(callSuper = true) // true -> 부모한테 있는 값까지 포함하여 비교, false -> 현재 Entity 클래스의 값으로만 비교.
@Entity
@Table(name = "useraccount")
public class AccountEntity extends BaseEntity {
}
