import React, { useEffect, useRef, useState, memo } from 'react';
import styles from './ToAddBlock.module.css';
import addIcon from '../../../images/ic_add.png';

const ToAddBlock = memo(({ block, addBlock }) => {
  return (
    <div id={block.id} className={styles.block} onClick={(e) => addBlock(block)}>
      <img src={addIcon} />
    </div>
  );
});

export default ToAddBlock;
