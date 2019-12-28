package com.chart.domain.charting;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Caiqin
 * @Date: 2019/12/21 15:52
 */
@Data
@ToString
@Entity
@Table(name = "chart_instance_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ChartInstance implements Serializable {
    private static final long serialVersionUID = -916357110051689486L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String name;
    private String category;
    private String owner;
    @Column(name = "private_in_public")
    private boolean privateInPublic;
    private String description;
    private String data;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    private String creator;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "flag_domain")
    private int flagDomain;
    @Column(name = "dataset_id")
    private String datasetId;
}
